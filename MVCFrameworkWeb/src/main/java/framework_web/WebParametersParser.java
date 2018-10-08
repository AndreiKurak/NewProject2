package framework_web;

import common.parser.ParametersParser;
import common.parser.ParseException;

import java.util.*;

public class WebParametersParser implements ParametersParser {
    
    private String command;
    private Map<String, Object> commandOptions = new HashMap<>();
    private Map<String, Object> globalOptions = new HashMap<>();

    public WebParametersParser(Map<String, String[]> inputMap) {
        if (inputMap.isEmpty())
            throw new ParseException("Request parameters not found");

        command = inputMap.get("command")[0];

        inputMap.forEach((key, value) -> {
            if (value[0] != "" && !value[0].equals(command))
                commandOptions.put(key, value[0]);
        });

        globalOptions.put("database", "test");
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
