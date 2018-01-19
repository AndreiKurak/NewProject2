package lib;

import java.util.ArrayList;
import java.util.List;

public class CommandDescription {
    public String name;
    public String[] commands = {"add", "list", "search", "update", "delete"};

    public String getName(){
        return name;
    }
    List<String> getOptions(){
        return null;
    }

    public void setName(String name){
        this.name = name;
    }
}
