package lib;

import commonPac.CommandDescription;
import commonPac.OptionDescription;

import java.util.ArrayList;
import java.util.List;

public class CommandEnumeration {

    OptionDescriptionBuilder author = new OptionDescriptionBuilder("author", new TypeValidator(new String()));
    OptionDescriptionBuilder title = new OptionDescriptionBuilder("title", new TypeValidator(new String()));
    OptionDescriptionBuilder year = new OptionDescriptionBuilder("year", new TypeValidator(new Integer(0)).linkWith(new DateBorderValidator()));
    OptionDescriptionBuilder id = new OptionDescriptionBuilder("id", new TypeValidator(new Integer(0)));

    List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(author.setNewMandatory(true).createOption());
        add(title.setNewMandatory(true).createOption());
        add(year.setNewMandatory(false).createOption());
    }};

    List<OptionDescription> searchOptions = new ArrayList<OptionDescription>(){{
        add(author.setNewMandatory(false).createOption());
        add(title.setNewMandatory(false).createOption());
        add(year.setNewMandatory(false).createOption());
    }};

    List<OptionDescription> deleteOptions = new ArrayList<OptionDescription>(){{
        add(id.setNewMandatory(true).createOption());
    }};

    List<OptionDescription> listOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("authors", false));
        add(new OptionDescription("titles", false));
        add(new OptionDescription("years", false));
        add(new OptionDescription("all", false));
    }};

    List<OptionDescription> updateOptions = new ArrayList<OptionDescription>(){{
        add(author.setNewMandatory(false).createOption());
        add(title.setNewMandatory(false).createOption());
        add(year.setNewMandatory(false).createOption());
        add(id.setNewMandatory(true).createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file1", true));
        add(new OptionDescription("file2", false));
        add(new OptionDescription("help", false));
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
