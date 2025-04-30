package com.logger.logger.appenders.implementation;

import com.logger.logger.appenders.interfaces.Appender;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class FileAppender implements Appender {
    private final String filePath;
    private final String LOGS_DIRECTORY = "logs";

    public FileAppender(String fileName){
        this.filePath = Paths.get(LOGS_DIRECTORY, fileName).toString();
        ensureLogFileExists();
    }

    private void ensureLogFileExists() {
        File logFile = new File(filePath);
        try {
            if (!logFile.getParentFile().exists()) logFile.getParentFile().mkdirs();
            if (!logFile.exists()) logFile.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create log file: " + e.getMessage());
        }
    }

    @Override
    public void append(String msg) {
        try (FileWriter fileWriter = new FileWriter(this.filePath, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(msg);
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
}
