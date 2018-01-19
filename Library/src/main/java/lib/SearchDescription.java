package lib;

import java.util.ArrayList;
import java.util.List;

public class SearchDescription extends CommandDescription {
    String name = "search";
    String description;
    List<String> options = new ArrayList<String>(){{
        add("author");
        add("title");
        add("year");
    }};

    public String getName(){
        return name;
    }

    List<String> getOptions(){
        return options;
    }
}
