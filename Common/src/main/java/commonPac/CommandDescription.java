package commonPac;

import java.util.List;

public class CommandDescription {

    private String name;
    private String description;
    private List<OptionDescription> options;
    private Command command;

    public CommandDescription(String name, String description, List<OptionDescription> options, Command command){
        this.name = name;
        this.description = description;
        this.options = options;
        this.command = command;
    }

    public String getName(){
        return name;
    }

    public List<OptionDescription> getOptions(){
        return options;
    }

    public Command getCommand() {
        return command;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setOptions(List<OptionDescription> options){
        this.options = options;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String toString(){
        return name + ": " + description + " " + options;
    }
}
