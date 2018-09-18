package framework_console;

import common.parser.ParametersParser;

public class ConsoleParametersParser implements ParametersParser {

    String[] input;

    public ConsoleParametersParser(String[] input) {
        this.input = input;
    }

    @Override
    public Object getInput() {
        return input;
    }
}
