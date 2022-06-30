package ru.nsu.kamkina.executor;

import ru.nsu.kamkina.commands.Executable;
import ru.nsu.kamkina.exceptions.*;
import ru.nsu.kamkina.factory.FactoryCommand;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class StackCalculator {

    private static final Logger logger = LogManager.getLogger();
    private static final String SEPARATOR_SPACE_REGEX = "\\s++";

    private BufferedReader reader;

    public StackCalculator(String inputStream) {
        try {
            if (inputStream == null) {
                reader = new BufferedReader(new InputStreamReader(System.in));
                logger.info("Reading from console");
            } else {
                reader = new BufferedReader(new FileReader(inputStream));
                logger.info("Reading from " + inputStream);
            }
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        }
    }

    public void calculate() {
        ExecutionContext context = new ExecutionContext();
        List<String> arguments;
        try {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] commandLine = currentLine.split(SEPARATOR_SPACE_REGEX);
                try {
                    Executable command = FactoryCommand.getInstance().buildCommand(commandLine[0]);
                    arguments = Arrays.asList(commandLine).subList(1, commandLine.length);
                    command.execute(context, arguments);
                } catch (CommandNotFoundException | CalculatorStackException | CalculatorArgumentException |
                         CalculatorArithmeticException ex) {
                    logger.error(ex);
                } catch (BuildCommandException ex) {
                    logger.error(ex.getCause());
                }
            }
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}
