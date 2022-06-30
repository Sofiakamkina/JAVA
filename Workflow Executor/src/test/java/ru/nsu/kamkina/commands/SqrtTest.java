package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArithmeticException;
import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SqrtTest {

    public static final Sqrt sqrt = new Sqrt(); // no state

    @Test
    public void positiveSqrt() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(4.0);
        try {
            sqrt.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Number is negative", ex.getMessage());
        }
        Assertions.assertEquals(2.0, context.getStack().peek());
    }

    @Test
    public void negativeSqrt() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-4.0);
        try {
            sqrt.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Not enough values to execute command", ex.getMessage());
        } catch (CalculatorArithmeticException ex) {
            Assertions.assertEquals("Number is negative", ex.getMessage());
        }
        Assertions.assertEquals(-4.0, context.getStack().peek());
    }
}
