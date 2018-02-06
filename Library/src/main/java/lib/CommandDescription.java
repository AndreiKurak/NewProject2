package lib;

import java.util.ArrayList;
import java.util.List;

public class CommandDescription {
    private String name;
    private String description;
    List<OptionDescription> options;

    public CommandDescription(String name, String description, List<OptionDescription> options){
        setName(name);
        setDescription(description);
        setOptions(options);
    }

    public String getName(){
        return name;
    }
    public List<OptionDescription> getOptions(){
        return options;
    }

    public String toString(){
        return name + ": " + description + " " + options;
    }

    public void setName(String name1){
        this.name = name1;
    }

    public void setDescription(String description1){
        this.description = description1;
    }

    public void setOptions(List<OptionDescription> options1){
        this.options = options1;
    }
}
