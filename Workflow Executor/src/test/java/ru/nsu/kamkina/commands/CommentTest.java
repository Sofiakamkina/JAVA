package ru.nsu.kamkina.commands;

import org.junit.jupiter.api.Test;

public class CommentTest {

    private static final Comment comment = new Comment(); // no state

    @Test
    public void comment() {
        comment.execute(null, null);
    }
}
