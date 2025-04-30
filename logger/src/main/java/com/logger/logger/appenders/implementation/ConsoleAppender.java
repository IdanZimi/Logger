package com.logger.logger.appenders.implementation;

import com.logger.logger.appenders.interfaces.Appender;

public class ConsoleAppender implements Appender {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLUE = "\u001B[34m";

    @Override
    public void append(String msg) {
        String coloredMsg = getColorForLog(msg) + msg + RESET;
        System.out.println(coloredMsg);
    }

    private String getColorForLog(String msg) {
        if (msg.contains("ERROR")) return RED;
        if (msg.contains("DEBUG")) return BLUE;
        if (msg.contains("INFO")) return GREEN;
        return RESET;
    }
}
