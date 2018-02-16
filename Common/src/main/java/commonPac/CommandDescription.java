package commonPac;

import java.util.List;

public class CommandDescription {

    private String name;
    private String description;
    private List<OptionDescription> options;

    public CommandDescription(String name, String description, List<OptionDescription> options){
        this.name = name;
        this.description = description;
        this.options = options;
    }

    public String getName(){
        return name;
    }

    public List<OptionDescription> getOptions(){
        return options;
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

    public String toString(){
        return name + ": " + description + " " + options;
    }
}
