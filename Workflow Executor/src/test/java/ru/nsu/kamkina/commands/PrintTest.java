package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrintTest {

    public static final Print print = new Print(); // no state

    @Test
    public void print() {
        ExecutionContext context = new ExecutionContext();
        try {
            print.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Stack is empty", ex.getMessage());
        }

        context.getStack().push(2.0);
        try {
            print.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Stack is empty", ex.getMessage());
        }
        Assertions.assertEquals(2.0, context.getStack().peek());
    }
}
