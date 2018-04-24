package lib;

import commonPac.Command;
import commonPac.CommandDescription;
import commonPac.OptionDescription;

import java.util.List;

public class CommandDescriptionBuilder {

    private String name;
    private String description;
    private List<OptionDescription> options;
    private Command command;

    public CommandDescriptionBuilder(String name) {
        this.name = name;
    }

    public CommandDescriptionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandDescriptionBuilder setOptions(List<OptionDescription> options) {
        this.options = options;
        return this;
    }

    public CommandDescriptionBuilder setCommand(Command command) {
        this.command = command;
        return this;
    }

    public CommandDescription createCommand(){
        return new CommandDescription(name, description, options, command);
    }
}
