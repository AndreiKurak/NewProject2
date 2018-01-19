package lib;

import java.util.ArrayList;
import java.util.List;

public class ListDescription extends CommandDescription {
    String name = "list";
    String description;
    List<String> options = new ArrayList<String>(){{
        add("all");
        add("authors");
        add("titles");
        add("years");
    }};

    public String getName(){
        return name;
    }

    List<String> getOptions(){
        return options;
    }
}
