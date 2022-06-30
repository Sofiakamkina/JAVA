package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.executor.ExecutionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Comment implements Executable {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> arguments) {
        logger.info("Comment command execute");
    }
}
