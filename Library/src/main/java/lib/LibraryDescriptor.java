package lib;

import common.ApplicationDescriptor;
import common.descriptions.CommandDescription;
import common.descriptions.CommandDescriptionBuilder;
import common.descriptions.OptionDescription;
import lib.commands_to_execute.*;
import lib.validators.DateBorderValidator;
import lib.validators.TypeValidator;

import java.util.ArrayList;
import java.util.List;

public class LibraryDescriptor implements ApplicationDescriptor {

    private List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file1").setMandatoryTrue());
        add(new OptionDescription("file2"));
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    private List<CommandDescription> commands = new ArrayList<CommandDescription>(){{
        add(new CommandDescriptionBuilder("add").
            setDescription("command, that is used for adding new books to the library").
            setOptions(new OptionDescription("author").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                       new OptionDescription("title").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                       new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
            setCommand(new AddCommandExecutor()).
            createCommand());
        add(new CommandDescriptionBuilder("search").
            setDescription("command, that is used for finding required book").
            setOptions(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                       new OptionDescription("title").addValidator(new TypeValidator(new String())),
                       new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
            setCommand(new SearchCommandExecutor()).
            createCommand());
        add(new CommandDescriptionBuilder("delete").
            setDescription("command, that is used for removing unnecessary or doubtful records").
            setOptions(new OptionDescription("id").addValidator(new TypeValidator(new Integer(0)))).
            setCommand(new DeleteCommandExecutor()).
            createCommand());
        add(new CommandDescriptionBuilder("list").
            setDescription("command, that is used to display the list with specified parameters").
            setOptions(new OptionDescription("authors"),
                       new OptionDescription("titles"),
                       new OptionDescription("years"),
                       new OptionDescription("all")).
            setCommand(new ListCommandExecutor()).
            createCommand());
        add(new CommandDescriptionBuilder("update").
            setDescription("command, that is used for rewriting some information").
            setOptions(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                       new OptionDescription("title").addValidator(new TypeValidator(new String())),
                       new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()),
                       new OptionDescription("id").setMandatoryTrue().addValidator(new TypeValidator(new Integer(0)))).
            setCommand(new UpdateCommandExecutor()).
            createCommand());
        add(new CommandDescriptionBuilder("help").
            setDescription("command, that is used for showing information about commands and options").
            createCommand());
    }};

    public List<CommandDescription> getCommandsList(){
        return commands;
    }

    public List<OptionDescription> getGlobalOptionsList(){
        return globalOptions;
    }
}
