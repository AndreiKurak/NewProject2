package common.descriptions;

import java.util.Arrays;
import java.util.List;

public class CommandDescriptionBuilder {

    private String name;
    private String description;
    private List<OptionDescription> optionsDescription;
    private Class command;
    private Class commandOptions;

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

    public CommandDescriptionBuilder setCommand(Class command) {
        this.command = command;
        return this;
    }

    public CommandDescriptionBuilder setCommandOptions(Class commandOptions){
        this.commandOptions = commandOptions;
        return this;
    }

    public CommandDescription createCommand(){
        return new CommandDescription(name, description, optionsDescription, command, commandOptions);
    }
}
