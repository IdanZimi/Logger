package com.logger.exampleFlow;

import com.logger.logManager.LogManager;
import com.logger.logger.Logger;
import com.logger.logger.enums.LogLevel;

public class ClassA {
    Logger logger;

    public ClassA() {
        this.logger = LogManager.INSTANCE.getLogger(ClassA.class);
    }

    public void makeLoggerWork() {
        LogManager logManager = LogManager.INSTANCE;
        String packageName = ClassA.class.getPackageName();

        logManager.setLogLevel(packageName, LogLevel.ERROR);
        logger.debug("will not be printed due to package logLevel");

        logger.setLogLevel(LogLevel.INFO);
        logger.debug("this message will be printed --> (DEBUG > INFO)");
    }
}
