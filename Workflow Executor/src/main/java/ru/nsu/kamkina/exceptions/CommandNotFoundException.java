package ru.nsu.kamkina.exceptions;

public class CommandNotFoundException extends Exception {

    public CommandNotFoundException(String message) {
        super(message);
    }
}