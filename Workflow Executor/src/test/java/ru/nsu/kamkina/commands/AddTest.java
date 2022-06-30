package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddTest {

    private static final Add add = new Add(); // no state

    @Test
    public void positiveAdd() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.0);
        context.getStack().push(3.0);
        try {
            add.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(5, context.getStack().peek());
    }

    @Test
    public void negativeAdd() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(-3.0);
        try {
            add.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(-5, context.getStack().peek());
    }

    @Test
    public void zeroAdd() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(0.0);
        context.getStack().push(0.0);
        try {
            add.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(0, context.getStack().peek());
    }

    @Test
    public void positiveAndNegativeAdd() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(3.0);
        try {
            add.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        }
        Assertions.assertEquals(1, context.getStack().peek());
    }
}
