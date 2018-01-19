package lib;

import java.util.ArrayList;
import java.util.List;

public class DeleteDescription extends CommandDescription {
    String name = "delete";
    String description;
    List<String> options = new ArrayList<String>(){{
        add("id");
    }};

    public String getName(){
        return name;
    }

    List<String> getOptions(){
        return options;
    }
}
