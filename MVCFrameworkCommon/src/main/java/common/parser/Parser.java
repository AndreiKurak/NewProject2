package common.parser;

import common.ApplicationDescriptor;
import common.CommandWithOptions;
import common.descriptions.CommandDescription;
import common.options_setter.OptionsSetter;
import common.descriptions.OptionDescription;

public class Parser {
    
    public CommandWithOptions parse(ParametersParser parametersParser, ApplicationDescriptor applicationDescriptor) {
        OptionsSetter optionsSetter = new OptionsSetter();
        CommandWithOptions command = new CommandWithOptions();

        CommandDescription commandDescription = null;
        for (CommandDescription description : applicationDescriptor.getCommandsDescriptionList()) {
            if (description.getName().equals(parametersParser.getCommand()))
                commandDescription = description;
        }

        if (commandDescription == null)
            throw new ParseException("Wrong command");

        command.setCommand(commandDescription.createAndGetCommand());
        command.setGlobalOptions(applicationDescriptor.getGlobalOptions());

        for (OptionDescription globalOption : applicationDescriptor.getGlobalOptionsDescriptionList())
            parametersParser.getGlobalOptions().forEach((key, value) -> {
                if (key.contains(globalOption.getName()))
                    optionsSetter.setOptions(globalOption.getName(), (String) value, command.getGlobalOptions());
            });

        command.setCommandOptions(commandDescription.createAndGetCommandOptions());
        if (commandDescription.getOptions() != null)
            for (OptionDescription option : commandDescription.getOptions())
                parametersParser.getCommandOptions().forEach((key, value) -> {
                    if (key.contains(option.getName())) {
                        optionsSetter.setOptions(option.getName(), (String) value, command.getCommandOptions());
                        /*
                        if (option.getValidator() != null && !option.getValidator().check((String) value))
                            throw new ParseException("Unacceptable value of option \"" + option.getName() + "\"");
                            */
                    }
                });

        return command;
    }
}
