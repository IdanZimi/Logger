package com.logger.logger;

import com.logger.logManager.LogManager;
import com.logger.logger.enums.LogLevel;
import com.logger.logger.formatter.implementation.LoggerFormatter;
import com.logger.logger.appenders.implementation.ConsoleAppender;
import com.logger.logger.appenders.interfaces.Appender;
import com.logger.logger.formatter.interfaces.Formatter;
import com.logger.logger.logTask.LogTask;
import com.logger.logger.logTask.LogTaskPool;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class Logger {
    @Getter
    private List<Appender> appenders;
    private final String context;
    private final Formatter formatter;
    private final LogTaskPool logTaskPool;

    public Logger(String context, LogTaskPool logTaskPool, Appender... appenders) {
        Appender[] appenderArray = (appenders.length > 0) ?  appenders : new Appender[]{new ConsoleAppender()};

        this.context = context;
        this.logTaskPool = logTaskPool;
        this.appenders = Arrays.asList(appenderArray);
        this.formatter = new LoggerFormatter(context);
    }

    private void log(LogLevel level, String msg) {
        LogLevel logLevel = LogManager.INSTANCE.getLogLevelWithFallback(this.context);

        if (level.ordinal() >= logLevel.ordinal()) {
            LogTask task = new LogTask(this, msg, this.formatter, level.name());
            logTaskPool.executeTask(task);
        }
    }

    public void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    public void setLogLevel(LogLevel level) {
        LogManager logManager = LogManager.INSTANCE;
        logManager.setLogLevel(this.context, level);
    }

    public void removeLogLevel(){
        LogManager logManager = LogManager.INSTANCE;
        logManager.removeLogLevel(this.context);
    }
}
