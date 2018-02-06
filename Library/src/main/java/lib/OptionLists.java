package lib;

import java.util.ArrayList;
import java.util.List;

public class OptionLists {

    OptionDescription auhtor = new OptionDescription("author", true);
    OptionDescription title = new OptionDescription("title", true);
    OptionDescription year = new OptionDescription("year", false);
    OptionDescription id = new OptionDescription("id", true);

    List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", true));
        add(new OptionDescription("title", true));
        add(new OptionDescription("year", false));
    }};

    List<OptionDescription> searchOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", false));
        add(new OptionDescription("title", false));
        add(new OptionDescription("year", false));
    }};

    List<OptionDescription> deleteOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("id", true));
    }};

    List<OptionDescription> listOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("authors", false));
        add(new OptionDescription("titles", false));
        add(new OptionDescription("years", false));
        add(new OptionDescription("all", false));
    }};

    List<OptionDescription> updateOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", false));
        add(new OptionDescription("title", false));
        add(new OptionDescription("year", false));
        add(new OptionDescription("id", true));
    }};

    public List<String> getOptName(List<OptionDescription> list){
        List<String> opt = new ArrayList<String>();
        for (int i = 0; i<list.size(); i++){
            opt.add(list.get(i).getName());
        }
        return opt;
    }
}
