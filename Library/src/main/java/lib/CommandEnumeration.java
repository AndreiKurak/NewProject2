package lib;

import commonPac.*;

import java.util.ArrayList;
import java.util.List;

public class CommandEnumeration {

    List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setMandatoryTrue().addValidator(new ValidatorsChain(new TypeValidator(new String()))).createOption());
        add(new OptionDescriptionBuilder("title").setMandatoryTrue().addValidator(new ValidatorsChain(new TypeValidator(new String()))).createOption());
        add(new OptionDescriptionBuilder("year").addValidator(new ValidatorsChain(new TypeValidator(new Integer(0)), new DateBorderValidator())).createOption());
    }};

    List<OptionDescription> searchOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").addValidator(new ValidatorsChain(new TypeValidator(new String()))).createOption());
        add(new OptionDescriptionBuilder("title").addValidator(new ValidatorsChain(new TypeValidator(new String()))).createOption());
        add(new OptionDescriptionBuilder("year").addValidator(new ValidatorsChain(new TypeValidator(new Integer(0)), new DateBorderValidator())).createOption());
    }};

    List<OptionDescription> deleteOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("id").addValidator(new ValidatorsChain(new TypeValidator(new Integer(0)))).createOption());
    }};

    List<OptionDescription> listOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("authors").createOption());
        add(new OptionDescriptionBuilder("titles").createOption());
        add(new OptionDescriptionBuilder("years").createOption());
        add(new OptionDescriptionBuilder("all").createOption());
    }};

    List<OptionDescription> updateOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").addValidator(new ValidatorsChain(new TypeValidator(new String()))).createOption());
        add(new OptionDescriptionBuilder("title").addValidator(new ValidatorsChain(new TypeValidator(new String()))).createOption());
        add(new OptionDescriptionBuilder("year").addValidator(new ValidatorsChain(new TypeValidator(new Integer(0)), new DateBorderValidator())).createOption());
        add(new OptionDescriptionBuilder("id").setMandatoryTrue().addValidator(new ValidatorsChain(new TypeValidator(new Integer(0)))).createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("file1").setMandatoryTrue().createOption());
        add(new OptionDescriptionBuilder("file2").createOption());
        add(new OptionDescriptionBuilder("help").createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescriptionBuilder("add").
            setDescription("command, that is used for adding new books to the library").
            setOptions(addOptions).
            setCommand(new AddCommand()).createCommand());
        add(new CommandDescriptionBuilder("search").
            setDescription("command, that is used for finding required book").
            setOptions(searchOptions).
            setCommand(new SearchCommand()).createCommand());
        add(new CommandDescriptionBuilder("delete").
            setDescription("command, that is used for removing unnecessary or doubtful records").
            setOptions(deleteOptions).
            setCommand(new DeleteCommand()).createCommand());
        add(new CommandDescriptionBuilder("list").
            setDescription("command, that is used to display the list with specified parameters").
            setOptions(listOptions).
            setCommand(new ListCommand()).createCommand());
        add(new CommandDescriptionBuilder("update").
            setDescription("command, that is used for rewriting some information").
            setOptions(updateOptions).
            setCommand(new UpdateCommand()).createCommand());
    }};
}
