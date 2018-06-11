package commonPac.descriptions;

import commonPac.Command;

import java.util.List;

public class CommandDescription {

    private String name;
    private String description;
    private List<OptionDescription> options;
    private Command commandToExecute;

    public CommandDescription(String name, String description, List<OptionDescription> options, Command commandToExecute){
        this.name = name;
        this.description = description;
        this.options = options;
        this.commandToExecute = commandToExecute;
    }

    public String getName(){
        return name;
    }

    public List<OptionDescription> getOptions(){
        return options;
    }

    public Command getCommandToExecute() {
        return commandToExecute;
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

    public void setCommandToExecute(Command commandToExecute) {
        this.commandToExecute = commandToExecute;
    }

    public String toString(){
        return name + ": " + description + " " + options;
    }
}
