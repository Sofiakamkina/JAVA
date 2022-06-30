package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArgumentException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Push implements Executable {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> arguments) throws CalculatorArgumentException {
        if (arguments.size() < 1) {
            throw new CalculatorArgumentException("Not enough argument");
        }
        try {
            if (context.getDefines().containsKey(arguments.get(0))) {
                context.getStack().push(context.getDefines().get(arguments.get(0)));
            } else {
                context.getStack().push(Double.valueOf(arguments.get(0)));
            }
        } catch (NumberFormatException ex) {
            logger.error(ex);
        }
        logger.info("Push command execute");
    }
}
