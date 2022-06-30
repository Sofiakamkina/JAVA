package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubtractTest {

    public static final Subtract subtract = new Subtract(); // no state

    @Test
    public void positiveSubtract() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.0);
        context.getStack().push(3.0);
        try {
            subtract.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(1.0, context.getStack().peek());
    }

    @Test
    public void negativeSubtract() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(-3.0);
        try {
            subtract.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(-1.0, context.getStack().peek());
    }

    @Test
    public void zeroSubtract() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(0.0);
        context.getStack().push(0.0);
        try {
            subtract.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().peek());
    }

    @Test
    public void positiveAndNegativeSubtract() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(3.0);
        try {
            subtract.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(5.0, context.getStack().peek());
    }
}
