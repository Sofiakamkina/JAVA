package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArgumentException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Define implements Executable {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> arguments) throws CalculatorArgumentException {
        if (arguments.size() < 2) {
            throw new CalculatorArgumentException("Not enough argument");
        }
        try {
            context.getDefines().put(arguments.get(0), Double.valueOf(arguments.get(1)));
        } catch (NumberFormatException ex) {
            logger.error(ex);
        }
        logger.info("Define command execute");
    }
}
