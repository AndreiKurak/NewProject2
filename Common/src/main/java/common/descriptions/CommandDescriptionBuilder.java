package common.descriptions;

import common.ExecutableCommand;

import java.util.Arrays;
import java.util.List;

public class CommandDescriptionBuilder {

    private String name;
    private String description;
    private List<OptionDescription> optionsDescription;
    private ExecutableCommand command;
    private Object commandOptions;
    private Object globalOptions;

    public CommandDescriptionBuilder(String name) {
        this.name = name;
    }

    public CommandDescriptionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandDescriptionBuilder setOptionsDescription(OptionDescription... optionsDescription) {
        this.optionsDescription = Arrays.asList(optionsDescription);
        return this;
    }

    public CommandDescriptionBuilder setCommand(ExecutableCommand command) {
        this.command = command;
        return this;
    }

    public CommandDescriptionBuilder setCommandOptions(Object commandOptions){
        this.commandOptions = commandOptions;
        return this;
    }

    public CommandDescription createCommand(){
        return new CommandDescription(name, description, optionsDescription, command, commandOptions);
    }
}
