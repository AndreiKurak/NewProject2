package web_app;

import common.ApplicationDescriptor;
import common.descriptions.CommandDescription;
import common.descriptions.CommandDescriptionBuilder;
import common.descriptions.OptionDescription;
import lib.command_options.*;
import lib.commands.*;
import lib.global_options.GlobalOptions;
import lib.validators.DateBorderValidator;
import lib.validators.TypeValidator;
import web_app.commands.AddCommandShow;
import web_app.commands.SearchCommandShow;
import web_app.commands.UpdateCommandShow;

import java.util.ArrayList;
import java.util.List;

public class WebLibraryDescriptor implements ApplicationDescriptor {

    GlobalOptions globalOptions = new GlobalOptions();

    private List<OptionDescription> globalOptionsDescription = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("database"));
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    private List<CommandDescription> commandsDescription = new ArrayList<CommandDescription>(){{
        add(new CommandDescriptionBuilder("showAdd").
                setCommandOptions(EmptyCommandOptions.class).
                setCommand(AddCommandShow.class)
                .createCommand());
        add(new CommandDescriptionBuilder("add").
                setOptionsDescription(new OptionDescription("author").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                        new OptionDescription("title").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                        new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
                setCommand(AddCommand.class).
                setCommandOptions(AddCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("showSearch").
                setCommand(SearchCommandShow.class).
                setCommandOptions(EmptyCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("search").
                setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                        new OptionDescription("title").addValidator(new TypeValidator(new String())),
                        new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
                setCommand(SearchCommand.class).
                setCommandOptions(SearchCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("delete").
                setOptionsDescription(new OptionDescription("id").addValidator(new TypeValidator(new Integer(0)))).
                setCommand(DeleteCommand.class).
                setCommandOptions(DeleteCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("list").
                setCommand(ListCommand.class).
                setCommandOptions(ListCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("showUpdate").
                setCommand(UpdateCommandShow.class).
                setCommandOptions(EmptyCommandOptions.class).
                createCommand());
        add(new CommandDescriptionBuilder("update").
                setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                        new OptionDescription("title").addValidator(new TypeValidator(new String())),
                        new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()),
                        new OptionDescription("id").setMandatoryTrue().addValidator(new TypeValidator(new Integer(0)))).
                setCommand(UpdateCommand.class).
                setCommandOptions(UpdateCommandOptions.class).
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
