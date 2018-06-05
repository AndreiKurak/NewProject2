package lib;

import commonPac.*;

import java.util.ArrayList;
import java.util.List;

public class CommandEnumeration {

    List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("file1").setMandatoryTrue().createOption());
        add(new OptionDescriptionBuilder("file2").createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescriptionBuilder("add").
            setDescription("command, that is used for adding new books to the library").
            setOptions(new OptionDescriptionBuilder("author").setMandatoryTrue().addValidator(new TypeValidator(new String())).createOption(),
                       new OptionDescriptionBuilder("title").setMandatoryTrue().addValidator(new TypeValidator(new String())).createOption(),
                       new OptionDescriptionBuilder("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()).createOption()).
            setCommand(new AddCommand()).createCommand());
        add(new CommandDescriptionBuilder("search").
            setDescription("command, that is used for finding required book").
            setOptions(new OptionDescriptionBuilder("author").addValidator(new TypeValidator(new String())).createOption(),
                       new OptionDescriptionBuilder("title").addValidator(new TypeValidator(new String())).createOption(),
                       new OptionDescriptionBuilder("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()).createOption()).
            setCommand(new SearchCommand()).createCommand());
        add(new CommandDescriptionBuilder("delete").
            setDescription("command, that is used for removing unnecessary or doubtful records").
            setOptions(new OptionDescriptionBuilder("id").addValidator(new TypeValidator(new Integer(0))).createOption()).
            setCommand(new DeleteCommand()).createCommand());
        add(new CommandDescriptionBuilder("list").
            setDescription("command, that is used to display the list with specified parameters").
            setOptions(new OptionDescriptionBuilder("authors").createOption(),
                       new OptionDescriptionBuilder("titles").createOption(),
                       new OptionDescriptionBuilder("years").createOption(),
                       new OptionDescriptionBuilder("all").createOption()).
            setCommand(new ListCommand()).createCommand());
        add(new CommandDescriptionBuilder("update").
            setDescription("command, that is used for rewriting some information").
            setOptions(new OptionDescriptionBuilder("author").addValidator(new TypeValidator(new String())).createOption(),
                       new OptionDescriptionBuilder("title").addValidator(new TypeValidator(new String())).createOption(),
                       new OptionDescriptionBuilder("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()).createOption(),
                       new OptionDescriptionBuilder("id").setMandatoryTrue().addValidator(new TypeValidator(new Integer(0))).createOption()).
            setCommand(new UpdateCommand()).createCommand());
        add(new CommandDescriptionBuilder("help").
            setDescription("command, that is used for showing information about commands and options").
            setCommand(new HelpCommand()).createCommand());
    }};
}
