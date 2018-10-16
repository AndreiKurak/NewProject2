package framework_web;

import common.parser.ParametersParser;
import common.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class WebParametersParser implements ParametersParser {
    
    private String command;
    private Map<String, Object> commandOptions = new HashMap<>();
    private Map<String, Object> globalOptions = new HashMap<>();

    public WebParametersParser(HttpServletRequest req) {
        Map<String, String[]> inputMap = req.getParameterMap();
        if (inputMap.isEmpty())
            throw new ParseException("Command options not found");

        command = req.getPathInfo().replace("/", "");

        inputMap.forEach((key, value) -> {
            if (value[0] != "")
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
