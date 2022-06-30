package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorStackException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PopTest {

    public static final Pop pop = new Pop(); // no state

    @Test
    public void singlePop() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.0);
        try {
            pop.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Stack is empty", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().size());
    }

    @Test
    public void doublePop() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.0);
        context.getStack().push(4.0);
        try {
            pop.execute(context, null);
            pop.execute(context, null);
        } catch (CalculatorStackException ex) {
            Assertions.assertEquals("Stack is empty", ex.getMessage());
        }
        Assertions.assertEquals(0.0, context.getStack().size());
    }
}
