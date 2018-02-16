package lib;

import commonPac.OptionDescription;

import java.util.ArrayList;
import java.util.List;

public class OptionLists {

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

}
