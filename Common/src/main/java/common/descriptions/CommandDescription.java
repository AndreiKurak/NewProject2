package common.descriptions;

import common.Command;

import java.util.List;

public class CommandDescription {

    private String name;
    private String description;
    private List<OptionDescription> options;
    private Class command;
    private Class optionsForCommand;

    public CommandDescription(String name, String description, List<OptionDescription> options, Class command, Class optionsForCommand){
        this.name = name;
        this.description = description;
        this.options = options;
        this.command = command;
        this.optionsForCommand = optionsForCommand;
    }

    public String getName(){
        return name;
    }

    public List<OptionDescription> getOptions(){
        return options;
    }
    /*
    public Object getCommandOptions() {
        return commandOptions;
    }
      */
    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setOptions(List<OptionDescription> options){
        this.options = options;
    }

    public void setCommand(Class command){
        this.command = command;
    }

    public Object createAndGetCommandOptions() {
        try{
            return (Object) optionsForCommand.newInstance();
        }
        catch (Exception ex){
            throw new RuntimeException(); //fix
        }

    }

    public Command createAndGetCommand() {
        try{
            return (Command) command.newInstance();
        }
        catch (Exception ex){
            throw new RuntimeException(); //fix
        }
    }


    public String toString(){
        return name + ": " + description + " " + options;
    }
}
