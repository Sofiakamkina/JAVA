package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Pop implements Executable {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> arguments) throws CalculatorStackException {
        if (context.getStack().size() == 0) {
            throw new CalculatorStackException("Stack is empty");
        }
        context.getStack().pop();
        logger.info("Pop command execute");
    }
}
