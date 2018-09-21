package common.parser;

import common.ApplicationDescriptor;
import common.CommandWithOptions;
import common.options_setter.OptionsSetter;
import common.descriptions.OptionDescription;

import java.util.*;

public class Parser {
    
    public CommandWithOptions parse(ParametersParser parametersParser, ApplicationDescriptor applicationDescriptor) {
        OptionsSetter optionsSetter = new OptionsSetter();
        CommandWithOptions command = new CommandWithOptions();

        command.setCommand(parametersParser.getCommand().createAndGetCommand());
        command.setGlobalOptions(applicationDescriptor.getGlobalOptions());

        parametersParser.getGlobalOptions().forEach((key, value) -> {
            optionsSetter.setOptions(key, (String) value, command.getGlobalOptions());
        });

        command.setCommandOptions(parametersParser.getCommand().createAndGetCommandOptions());
        List<OptionDescription> options = parametersParser.getCommand().getOptions();

        parametersParser.getCommandOptions().forEach((key, value) -> {
            for (int i = 0; i<options.size(); i++) {
                optionsSetter.setOptions(key, (String) value, command.getCommandOptions());
                if (options.get(i).getValidator() != null && !options.get(i).getValidator().check((String) value))
                    throw new ParseException("Unacceptable option value");
            }
        });

        return command;
    }
}
