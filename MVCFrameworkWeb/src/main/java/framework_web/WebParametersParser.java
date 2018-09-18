package framework_web;

import common.parser.ParametersParser;

import java.util.Map;

public class WebParametersParser implements ParametersParser {

    Map<String, String[]> input;

    public WebParametersParser(Map input) {
        this.input = input;
    }

    @Override
    public Object getInput() {
        for (String[] parameters: input.values()) {
            return parameters;
        }
        return null;
    }
}
