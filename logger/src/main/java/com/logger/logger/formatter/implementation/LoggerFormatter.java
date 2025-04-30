package com.logger.logger.formatter.implementation;

import com.logger.logger.formatter.interfaces.Formatter;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class LoggerFormatter implements Formatter {
    private final String context;

    @Override
    public String format(String message, String level) {
        String timestamp = new Date().toString();
        String threadId = Thread.currentThread().getName();

        return String.format("%s ([%s]) %s [%s] - %s", timestamp, threadId, level, this.context, message);
    }
}
