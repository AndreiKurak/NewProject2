package lib;

import commonPac.CommandDescription;
import commonPac.OptionDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandList {

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
    ////////////////////////////////////////////////////////////////////////////////////////
    List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file1", true));
        add(new OptionDescription("file2", false));
        add(new OptionDescription("help", false));
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "add - command, that is used for adding new books to the library", addOptions));
        add(new CommandDescription("search", "search - command, that is used for finding required book", searchOptions));
        add(new CommandDescription("delete", "delete - command, that is used for removing unnecessary or doubtful records", deleteOptions));
        add(new CommandDescription("list", "list - command, that is used to display the list with specified parameters", listOptions));
        add(new CommandDescription("update", "update - command, that is used for rewriting some information", updateOptions));
    }};
}
