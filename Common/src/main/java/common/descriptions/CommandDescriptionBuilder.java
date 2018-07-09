package common.descriptions;

import common.ExecutableCommand;

import java.util.Arrays;
import java.util.List;

public class CommandDescriptionBuilder {

    private String name;
    private String description;
    private List<OptionDescription> options;
    private ExecutableCommand command;

    public CommandDescriptionBuilder(String name) {
        this.name = name;
    }

    public CommandDescriptionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandDescriptionBuilder setOptions(OptionDescription... options) {
        this.options = Arrays.asList(options);
        return this;
    }

    public CommandDescriptionBuilder setCommand(ExecutableCommand command) {
        this.command = command;
        return this;
    }

    public CommandDescription createCommand(){
        return new CommandDescription(name, description, options, command);
    }
}
