package lib;

import commonPac.CommandDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandList {

    OptionLists optionList = new OptionLists();

    public List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "add - command, that is used for adding new books to the library", optionList.addOptions));
        add(new CommandDescription("search", "search - command, that is used for finding required book", optionList.searchOptions));
        add(new CommandDescription("delete", "delete - command, that is used for removing unnecessary or doubtful records", optionList.deleteOptions));
        add(new CommandDescription("list", "list - command, that is used to display the list with specified parameters", optionList.listOptions));
        add(new CommandDescription("update", "update - command, that is used for rewriting some information", optionList.updateOptions));
    }};

}
