package com.logger.exampleFlow;

import com.logger.logManager.LogManager;
import com.logger.logger.Logger;
import com.logger.logger.appenders.implementation.ConsoleAppender;
import com.logger.logger.appenders.implementation.FileAppender;
import com.logger.logger.enums.LogLevel;

public class ClassB {
    Logger logger;

    public ClassB() {
        this.logger = LogManager.INSTANCE.getLogger(
                ClassB.class,
                new ConsoleAppender(),
                new FileAppender("logger.txt")
        );
    }

    public void makeLoggerWork(){
        String PACKAGE_FOR_EXAMPLE = "com.logger";
        LogManager.INSTANCE.setLogLevel(PACKAGE_FOR_EXAMPLE, LogLevel.ERROR);

        logger.info("This message will not be printed due to package logLevel (com.logger - LogLevel.ERROR)");
        logger.error("This message will be printed (com.logger - LogLevel.ERROR)");

        logger.setLogLevel(LogLevel.DEBUG);
        logger.debug("This message will be printed (LogLevel.DEBUG)");

        logger.removeLogLevel();
        logger.debug("This message will not be printed (no log level, hence package level affect)");

        LogManager.INSTANCE.removeLogLevel(PACKAGE_FOR_EXAMPLE);
        logger.info("This message will be printed (no log level, no package level, default is INFO)");
        logger.debug("This message will be printed (DEBUG > INFO)");
        logger.error("This message will be printed (ERROR > INFO)");
    }
}
