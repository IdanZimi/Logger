package com.logger;

import com.logger.exampleFlow.ClassA;
import com.logger.exampleFlow.ClassB;
import com.logger.logManager.LogManager;

/*
* There are 2 floes I chose to show, first and second.
* Please uncomment the desired one and comment the other
* before running.
* */
public class Main {
    public static void main(String[] args) {
        ClassA first = new ClassA();
        ClassB second = new ClassB();

        //first.makeLoggerWork();
        second.makeLoggerWork();

        LogManager.INSTANCE.shutDown();
    }
}