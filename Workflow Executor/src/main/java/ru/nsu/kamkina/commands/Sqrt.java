package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArithmeticException;
import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Sqrt implements Executable {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> arguments) throws CalculatorStackException,
                                                                                 CalculatorArithmeticException {
        if (context.getStack().size() < 1) {
            throw new CalculatorStackException("Not enough values to execute command");
        }
        double a = context.getStack().pop();
        if (a < 0) {
            context.getStack().push(a);
            throw new CalculatorArithmeticException("Number is negative");
        }
        context.getStack().push(Math.sqrt(a));
        logger.info("Sqrt command execute");
    }
}
