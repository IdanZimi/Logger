package com.logger.logManager;

import com.logger.constants.Constants;
import com.logger.logger.appenders.interfaces.Appender;
import com.logger.logger.logTask.LogTaskPool;
import com.logger.logger.Logger;
import com.logger.logger.enums.LogLevel;

import java.util.Map;
import java.util.concurrent.*;

public enum LogManager {
    INSTANCE;

    private final Map<String, Logger> loggerMap = new ConcurrentHashMap<>();
    private final Map<String, LogLevel> loggerLogLevelMap = new ConcurrentHashMap<>();
    private final LogTaskPool taskPool = new LogTaskPool();
    private final String PACKAGE_DELIMITER = ".";
    private final String EMPTY_STRING = "";


    private LogLevel getLogLevel(String context) {
        return loggerLogLevelMap.get(context);
    }

    private String getPackageName(String context) {
        int lastIndex = context.lastIndexOf(PACKAGE_DELIMITER);
        return (lastIndex != -1) ? context.substring(0, lastIndex) : EMPTY_STRING;
    }

    public Logger getLogger(Class<?> context, Appender ... appenders) {
        String fullQualifiedName = context.getName();

        return loggerMap.computeIfAbsent(fullQualifiedName, key -> new Logger(key, taskPool, appenders));
    };

    public LogLevel getLogLevelWithFallback(String context) {
        while (!context.isEmpty()) {
            LogLevel level = getLogLevel(context);
            if (level != null) {
                return level;
            }
            context = getPackageName(context);
        }

        return Constants.DEFAULT_LOG_LEVEL;
    }

    public void setLogLevel(String context, LogLevel level) {
        loggerLogLevelMap.put(context, level);
    }

    public void removeLogLevel(String context) {
        loggerLogLevelMap.remove(context);
    }

    public void shutDown(){
        this.taskPool.shutdown();
    }
}
