package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiplyTest {

    private static final Multiply multiply = new Multiply(); // no state

    @Test
    public void positiveByPositiveMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.0);
        context.getStack().push(4.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(8.0, context.getStack().peek());
    }

    @Test
    public void positiveByNegativeMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.0);
        context.getStack().push(-4.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(-8.0, context.getStack().peek());
    }

    @Test
    public void negativeByPositiveMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(4.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(-8.0, context.getStack().peek());
    }

    @Test
    public void negativeByNegativeMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(-4.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(8.0, context.getStack().peek());
    }

    @Test
    public void NumberByZeroMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(4.0);
        context.getStack().push(0.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().peek());
    }

    @Test
    public void ZeroByNumberMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(0.0);
        context.getStack().push(4.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().peek());
    }

    @Test
    public void zeroByZeroMultiply() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(0.0);
        context.getStack().push(0.0);
        try {
            multiply.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().peek());
    }
}
