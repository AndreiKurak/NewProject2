package console_app;

import common.ApplicationDescriptor;
import common.descriptions.CommandDescription;
import common.descriptions.CommandDescriptionBuilder;
import common.descriptions.OptionDescription;
import lib.command_options.*;
import lib.commands.*;
import lib.global_options.GlobalOptions;
import lib.validators.DateBorderValidator;
import lib.validators.TypeValidator;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLibraryDescriptor implements ApplicationDescriptor {

    GlobalOptions globalOptions = new GlobalOptions();

    private List<OptionDescription> globalOptionsDescription = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file"));
        add(new OptionDescription("database"));
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    private List<CommandDescription> commandsDescription = new ArrayList<CommandDescription>(){{
        add(new CommandDescriptionBuilder("add").
                setDescription("command, that is used for adding new books to the library").
                setOptionsDescription(new OptionDescription("author").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                        new OptionDescription("title").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                        new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
                setCommand(AddCommand.class).
                setCommandOptions(AddCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("search").
                setDescription("command, that is used for finding required book").
                setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                        new OptionDescription("title").addValidator(new TypeValidator(new String())),
                        new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
                setCommand(SearchCommand.class).
                setCommandOptions(SearchCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("delete").
                setDescription("command, that is used for removing unnecessary or doubtful records").
                setOptionsDescription(new OptionDescription("id").addValidator(new TypeValidator(new Integer(0)))).
                setCommand(DeleteCommand.class).
                setCommandOptions(DeleteCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("list").
                setDescription("command, that is used to display the list with specified parameters").
                setCommand(ListCommand.class).
                setCommandOptions(EmptyCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("update").
                setDescription("command, that is used for rewriting some information").
                setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                        new OptionDescription("title").addValidator(new TypeValidator(new String())),
                        new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()),
                        new OptionDescription("id").setMandatoryTrue().addValidator(new TypeValidator(new Integer(0)))).
                setCommand(UpdateCommand.class).
                setCommandOptions(UpdateCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("help").
                setDescription("command, that is used for showing information about commandsDescription and options").
                setCommand(HelpCommand.class).
                setCommandOptions(EmptyCommandOptions.class).
                createCommand());
    }};

    public List<CommandDescription> getCommandsDescriptionList(){ //commandDescription
        return commandsDescription;
    }

    public List<OptionDescription> getGlobalOptionsDescriptionList(){ //globalDescription
        return globalOptionsDescription;
    }

    public Object getGlobalOptions(){
        return globalOptions;
    }
}
