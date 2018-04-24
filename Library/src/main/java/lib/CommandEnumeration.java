package lib;

import commonPac.CommandDescription;
import commonPac.OptionDescription;
import commonPac.OptionValidator;
import commonPac.ValidatorsChain;

import java.util.ArrayList;
import java.util.List;

public class CommandEnumeration {

    List<OptionValidator> authorAndTitleValidators = new ArrayList<OptionValidator>(){{
        add(new TypeValidator(new String()));
    }};

    List<OptionValidator> yearValidators = new ArrayList<OptionValidator>(){{
        add(new TypeValidator(new Integer(0)));
        add(new DateBorderValidator());
    }};

    List<OptionValidator> idValidators = new ArrayList<OptionValidator>(){{
        add(new TypeValidator(new Integer(0)));
    }};

    List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setMandatoryTrue().setValidator(new ValidatorsChain(authorAndTitleValidators)).createOption());
        add(new OptionDescriptionBuilder("title").setMandatoryTrue().setValidator(new ValidatorsChain(authorAndTitleValidators)).createOption());
        add(new OptionDescriptionBuilder("year").setValidator(new ValidatorsChain(yearValidators)).createOption());
    }};

    List<OptionDescription> searchOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setValidator(new ValidatorsChain(authorAndTitleValidators)).createOption());
        add(new OptionDescriptionBuilder("title").setValidator(new ValidatorsChain(authorAndTitleValidators)).createOption());
        add(new OptionDescriptionBuilder("year").setValidator(new ValidatorsChain(yearValidators)).createOption());
    }};

    List<OptionDescription> deleteOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("id").setValidator(new ValidatorsChain(idValidators)).createOption());
    }};

    List<OptionDescription> listOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("authors").createOption());
        add(new OptionDescriptionBuilder("titles").createOption());
        add(new OptionDescriptionBuilder("years").createOption());
        add(new OptionDescriptionBuilder("all").createOption());
    }};

    List<OptionDescription> updateOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("author").setValidator(new ValidatorsChain(authorAndTitleValidators)).createOption());
        add(new OptionDescriptionBuilder("title").setValidator(new ValidatorsChain(authorAndTitleValidators)).createOption());
        add(new OptionDescriptionBuilder("year").setValidator(new ValidatorsChain(yearValidators)).createOption());
        add(new OptionDescriptionBuilder("id").setMandatoryTrue().setValidator(new ValidatorsChain(idValidators)).createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescriptionBuilder("file1").setMandatoryTrue().createOption());
        add(new OptionDescriptionBuilder("file2").createOption());
        add(new OptionDescriptionBuilder("help").createOption());
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescriptionBuilder("add").setDescription("command, that is used for adding new books to the library").
            setOptions(addOptions).setCommand(new AddCommand()).createCommand());
        add(new CommandDescriptionBuilder("search").setDescription("command, that is used for finding required book").
            setOptions(searchOptions).setCommand(new SearchCommand()).createCommand());
        add(new CommandDescriptionBuilder("delete").setDescription("command, that is used for removing unnecessary or doubtful records").
            setOptions(deleteOptions).setCommand(new DeleteCommand()).createCommand());
        add(new CommandDescriptionBuilder("list").setDescription("command, that is used to display the list with specified parameters").
            setOptions(listOptions).setCommand(new ListCommand()).createCommand());
        add(new CommandDescriptionBuilder("update").setDescription("command, that is used for rewriting some information").
            setOptions(updateOptions).setCommand(new UpdateCommand()).createCommand());
    }};
}
