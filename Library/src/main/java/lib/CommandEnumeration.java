package lib;

import commonPac.CommandDescription;
import commonPac.OptionDescription;

import java.util.ArrayList;
import java.util.List;

public class CommandEnumeration {

    List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setMandatoryTrue().setValidator(new TypeValidator(new String())).createOption());
        add(new OptionDescriptionBuilder("title").setMandatoryTrue().setValidator(new TypeValidator(new String())).createOption());
        add(new OptionDescriptionBuilder("year").setValidator(new TypeValidator(new Integer(0)).linkWith(new DateBorderValidator())).createOption());
    }};

    List<OptionDescription> searchOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setValidator(new TypeValidator(new String())).createOption());
        add(new OptionDescriptionBuilder("title").setValidator(new TypeValidator(new String())).createOption());
        add(new OptionDescriptionBuilder("year").setValidator(new TypeValidator(new Integer(0)).linkWith(new DateBorderValidator())).createOption());
    }};

    List<OptionDescription> deleteOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("id").setValidator(new TypeValidator(new Integer(0))).createOption());
    }};

    List<OptionDescription> listOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("authors").createOption());
        add(new OptionDescriptionBuilder("titles").createOption());
        add(new OptionDescriptionBuilder("years").createOption());
        add(new OptionDescriptionBuilder("all").createOption());
    }};

    List<OptionDescription> updateOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setValidator(new TypeValidator(new String())).createOption());
        add(new OptionDescriptionBuilder("title").setValidator(new TypeValidator(new String())).createOption());
        add(new OptionDescriptionBuilder("year").setValidator(new TypeValidator(new Integer(0)).linkWith(new DateBorderValidator())).createOption());
        add(new OptionDescriptionBuilder("id").setMandatoryTrue().setValidator(new TypeValidator(new Integer(0))).createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("file1").setMandatoryTrue().createOption());
        add(new OptionDescriptionBuilder("file2").createOption());
        add(new OptionDescriptionBuilder("help").createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "add - command, that is used for adding new books to the library", addOptions, new AddCommand()));
        add(new CommandDescription("search", "search - command, that is used for finding required book", searchOptions, new SearchCommand()));
        add(new CommandDescription("delete", "delete - command, that is used for removing unnecessary or doubtful records", deleteOptions, new DeleteCommand()));
        add(new CommandDescription("list", "list - command, that is used to display the list with specified parameters", listOptions, new ListCommand()));
        add(new CommandDescription("update", "update - command, that is used for rewriting some information", updateOptions, new UpdateCommand()));
    }};
}
