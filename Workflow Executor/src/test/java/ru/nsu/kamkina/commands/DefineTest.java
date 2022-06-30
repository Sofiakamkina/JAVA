package ru.nsu.kamkina.commands;

import ru.nsu.kamkina.exceptions.CalculatorArgumentException;
import ru.nsu.kamkina.executor.ExecutionContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DefineTest {

    private static final Define define = new Define(); // no state

    @Test
    public void stringDefine() {
        ExecutionContext context = new ExecutionContext();
        try {
            define.execute(context, List.of("value", "10"));
        } catch (CalculatorArgumentException ex) {
            Assertions.assertEquals("Not enough argument", ex.getMessage());
        }
        Assertions.assertTrue(context.getDefines().containsKey("value"));
        Assertions.assertEquals(10.0, context.getDefines().get("value"));
    }
}
