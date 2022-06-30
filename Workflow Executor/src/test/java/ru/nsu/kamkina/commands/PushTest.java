package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArgumentException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PushTest {

    public static final Push push = new Push();

    @Test
    public void push() {
        ExecutionContext context = new ExecutionContext();
        try {
            push.execute(context, List.of("2.0"));
            push.execute(context, List.of("3.0"));
        } catch (CalculatorArgumentException ex) {
            Assertions.assertEquals("Not enough argument", ex.getMessage());
        }
        Assertions.assertEquals(2, context.getStack().size());
        Assertions.assertEquals(3.0, context.getStack().pop());
        Assertions.assertEquals(2.0, context.getStack().pop());
    }
}
