package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandList {

    OptionLists optionList = new OptionLists();

    CommandDescription add1 = new CommandDescription("add", "add - command, that is used for adding new books to the library", optionList.addOptions);
    CommandDescription search1 = new CommandDescription("search", "search - command, that is used for finding required book", optionList.searchOptions);
    CommandDescription delete1 = new CommandDescription("delete", "delete - command, that is used for removing unnecessary or doubtful records", optionList.deleteOptions);
    CommandDescription list1 = new CommandDescription("list", "list - command, that is used to display the list with specified parameters", optionList.listOptions);
    CommandDescription update1 = new CommandDescription("update", "update - command, that is used for rewriting some information", optionList.updateOptions);

    public Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
        put(add1.getName(), optionList.getOptName(optionList.addOptions));
        put(search1.getName(), optionList.getOptName(optionList.searchOptions));
        put(list1.getName(), optionList.getOptName(optionList.listOptions));
        put(update1.getName(), optionList.getOptName(optionList.updateOptions));
        put(delete1.getName(), optionList.getOptName(optionList.deleteOptions));
    }};

}
