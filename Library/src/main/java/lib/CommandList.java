package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandList {

    AddDescription add = new AddDescription();
    DeleteDescription delete = new DeleteDescription();
    ListDescription list = new ListDescription();
    SearchDescription search = new SearchDescription();
    UpdateDescription update = new UpdateDescription();

    public Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
        put(add.getName(), add.getOptions());
        put(search.getName(), search.getOptions());
        put(list.getName(), list.getOptions());
        put(update.getName(), update.getOptions());
        put(delete.getName(), delete.getOptions());
    }};
    /*  static List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new AddDescription());
        add(new DeleteDescription());
        add(new UpdateDescription());
        add(new ListDescription());
        add(new SearchDescription());
    }};   */
}
