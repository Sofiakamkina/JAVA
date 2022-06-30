package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Add implements Executable {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> arguments) throws CalculatorStackException {
        if (context.getStack().size() < 2) {
            throw new CalculatorStackException("Not enough values to execute command");
        }
        double a = context.getStack().pop();
        double b = context.getStack().pop();
        context.getStack().push(a + b);
        logger.info("Add command execute");
    }
}
