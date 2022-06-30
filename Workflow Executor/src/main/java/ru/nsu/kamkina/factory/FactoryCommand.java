package ru.nsu.kamkina.factory;

import ru.nsu.kamkina.exceptions.BuildCommandException;
import ru.nsu.kamkina.exceptions.CommandNotFoundException;
import ru.nsu.kamkina.commands.Executable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class FactoryCommand {

    private static final Logger logger = LogManager.getLogger();
    private final Properties commands = new Properties();

    private static FactoryCommand instance;

    public static FactoryCommand getInstance() {
        if (instance == null) {
            instance = new FactoryCommand();
        }
        return instance;
    }

    private FactoryCommand() {
        try {
            commands.load(FactoryCommand.class.getClassLoader().getResourceAsStream("commands.properties"));
            logger.info("Load commands.properties");
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    public Executable buildCommand(String commandName) throws CommandNotFoundException, BuildCommandException {
        if (!commands.containsKey(commandName)) {
            throw new CommandNotFoundException("Command doesn't exist");
        }
        Executable command;
        try {
            command = (Executable) Class.forName(commands.getProperty(commandName))
                                        .getDeclaredConstructor()
                                        .newInstance();
            logger.info("Build " + command.getClass().getSimpleName());
        } catch (Exception ex) {
            throw new BuildCommandException("Failed to create command", ex);
        }
        return command;
    }
}
