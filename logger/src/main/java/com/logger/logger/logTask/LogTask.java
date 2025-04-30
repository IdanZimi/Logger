package com.logger.logger.logTask;

import com.logger.logger.Logger;
import com.logger.logger.formatter.interfaces.Formatter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogTask implements Runnable {
    private Logger logger;
    private String msg;
    private Formatter formatter;
    private String level;


    @Override
    public void run() {
        String formattedMsg = formatter.format(msg, this.level);
        logger.getAppenders().forEach(appender -> appender.append(formattedMsg));
    }
}
