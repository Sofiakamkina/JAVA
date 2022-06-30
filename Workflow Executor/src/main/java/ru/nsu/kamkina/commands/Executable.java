package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArgumentException;
import ru.nsu.kamkina.exceptions.CalculatorArithmeticException;
import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import java.util.List;

public interface Executable {

    public void execute(ExecutionContext context, List<String> arguments) throws CalculatorStackException, CalculatorArgumentException, CalculatorArithmeticException;
}