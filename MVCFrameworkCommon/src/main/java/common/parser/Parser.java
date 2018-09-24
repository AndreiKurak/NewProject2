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
        for (OptionDescription option : options) {
            parametersParser.getCommandOptions().forEach((key, value) -> {
                if (option.getName().equals(key)) {
                    optionsSetter.setOptions(key, (String) value, command.getCommandOptions());
                    if (option.getValidator() != null && !option.getValidator().check((String) value))
                        throw new ParseException("Unacceptable option value");
                }
            });
        }

        return command;
    }
}
