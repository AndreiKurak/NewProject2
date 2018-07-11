package common.descriptions;

import common.ExecutableCommand;

import java.util.List;

public class CommandDescription {

    private String name;
    private String description;
    private List<OptionDescription> options;
    private ExecutableCommand commandToExecute;
    private Object commandOptions;
    private Object globalOptions;

    public CommandDescription(String name, String description, List<OptionDescription> options, ExecutableCommand commandToExecute, Object commandOptions){
        this.name = name;
        this.description = description;
        this.options = options;
        this.commandToExecute = commandToExecute;
        this.commandOptions = commandOptions;
    }

    public String getName(){
        return name;
    }

    public List<OptionDescription> getOptions(){
        return options;
    }

    public ExecutableCommand getCommandToExecute() {
        return commandToExecute;
    }

    public Object getCommandOptions() {
        return commandOptions;
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

    public void setCommandToExecute(ExecutableCommand commandToExecute) {
        this.commandToExecute = commandToExecute;
    }

    public void setCommandOptions(Object commandOptions) {
        this.commandOptions = commandOptions;
    }

    public String toString(){
        return name + ": " + description + " " + options;
    }
}
