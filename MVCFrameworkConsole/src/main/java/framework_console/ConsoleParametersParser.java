package framework_console;

import common.parser.ParametersParser;
import common.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class ConsoleParametersParser implements ParametersParser {
    
    private String command;
    private Map<String, Object> commandOptions = new HashMap<>();
    private Map<String, Object> globalOptions = new HashMap<>();

    private static final String EQUALITY = "=";

    public ConsoleParametersParser(String[] input) {
        if (input == null)
            throw new ParseException("Empty input line");

        int optionsIndex = 0;
        for (int i = 0; i<input.length; i++) 
            if (input[i].contains(EQUALITY))
                globalOptions.put(input[i].substring(0, input[i].indexOf(EQUALITY)), input[i].substring(input[i].indexOf(EQUALITY) + 1));
            else {
                command = input[i];
                optionsIndex = i;
                break;
            }

        for (int i = optionsIndex; i<input.length; i++)
            if (input[i].contains(EQUALITY))
                commandOptions.put(input[i].substring(0, input[i].indexOf(EQUALITY)), input[i].substring(input[i].indexOf(EQUALITY) + 1));
            else
                commandOptions.put(input[i], input[i]);
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Map<String, Object> getCommandOptions() {
        return commandOptions;
    }

    @Override
    public Map<String, Object> getGlobalOptions() {
        return globalOptions;
    }
}
