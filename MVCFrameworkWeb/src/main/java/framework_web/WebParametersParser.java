package framework_web;

import common.descriptions.CommandDescription;
import common.parser.ParametersParser;

import java.util.Map;

public class WebParametersParser implements ParametersParser {

    private Map<String, String[]> input;

    private CommandDescription command;
    private Map<String, Object> commandOptions;
    private Map<String, Object> globalOptions;

    public WebParametersParser(Map input) {
        //set fields
    }

    @Override
    public CommandDescription getCommand() {
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
