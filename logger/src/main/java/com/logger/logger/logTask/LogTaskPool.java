package com.logger.logger.logTask;

import java.util.concurrent.*;

public class LogTaskPool {
    private final ExecutorService executor;

    public LogTaskPool(){
        executor = Executors.newSingleThreadExecutor();
    }

    public void executeTask(LogTask task){
        executor.execute(task);
    }

    public void shutdown(){
        executor.shutdown();
    }
}
