package common.parser;

import common.descriptions.CommandDescription;

import java.util.Map;

public interface ParametersParser {

    CommandDescription getCommand();

    Map<String, Object> getCommandOptions();

    Map<String, Object> getGlobalOptions();
}
