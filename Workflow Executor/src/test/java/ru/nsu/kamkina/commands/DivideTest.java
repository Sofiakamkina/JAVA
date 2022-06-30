package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArithmeticException;
import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivideTest {

    private static final Divide divide = new Divide(); // no state

    @Test
    public void positiveByPositiveDivide() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(4.0);
        context.getStack().push(8.0);
        try {
            divide.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Division by zero", ex.getMessage());
        }
        Assertions.assertEquals(2, context.getStack().peek());
    }

    @Test
    public void positiveByNegativeDivide() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-4.0);
        context.getStack().push(8.0);
        try {
            divide.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Division by zero", ex.getMessage());
        }
        Assertions.assertEquals(-2, context.getStack().peek());
    }

    @Test
    public void negativeByPositiveDivide() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(4.0);
        context.getStack().push(-8.0);
        try {
            divide.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Division by zero", ex.getMessage());
        }
        Assertions.assertEquals(-2, context.getStack().peek());
    }

    @Test
    public void negativeByNegativeDivide() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-4.0);
        context.getStack().push(-8.0);
        try {
            divide.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Division by zero", ex.getMessage());
        }
        Assertions.assertEquals(2, context.getStack().peek());
    }

    @Test
    public void NumberByZeroDivide() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(0.0);
        context.getStack().push(4.0);
        try {
            divide.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Division by zero", ex.getMessage());
        }
        Assertions.assertEquals(4.0, context.getStack().peek());
    }

    @Test
    public void ZeroByNumber() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(4.0);
        context.getStack().push(0.0);
        try {
            divide.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Division by zero", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().peek());
    }
}
