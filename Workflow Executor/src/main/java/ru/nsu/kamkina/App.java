package ru.nsu.kamkina;

import ru.nsu.kamkina.executor.StackCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger();
    private static final int INPUT_FILE_NAME = 0;

    public static void main(String[] args) {
        logger.info("Application start");
        new StackCalculator(args.length == 0 ? null : args[INPUT_FILE_NAME]).calculate();
    }
}
