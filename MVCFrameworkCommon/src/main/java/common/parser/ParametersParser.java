package common.parser;

import java.util.Map;

public interface ParametersParser {

    String getCommand();

    Map<String, Object> getCommandOptions();

    Map<String, Object> getGlobalOptions();
}
