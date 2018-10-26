package lib;

import common.ApplicationDescriptor;
import common.descriptions.CommandDescription;
import common.descriptions.CommandDescriptionBuilder;
import common.descriptions.OptionDescription;
import lib.command_options.*;
import lib.commands.*;
import lib.global_options.GlobalOptions;
import lib.validators.DateBorderValidator;
import lib.validators.TypeValidator;
import lib.validators2.DateBorderValidator2;
import lib.validators2.TypeValidator2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryDescriptor {

    private List<OptionDescription> globalOptionsDescription = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file"));
        add(new OptionDescription("database"));
    }};
    ////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, CommandDescription> commandDescriptionMap = new HashMap<String, CommandDescription>(){{
        put("add",
            new CommandDescriptionBuilder("add").
            setOptionsDescription(new OptionDescription("author").setMandatoryTrue().addValidator(new TypeValidator2(new String())),
                    new OptionDescription("title").setMandatoryTrue().addValidator(new TypeValidator2(new String())),
                    new OptionDescription("year").addValidator(new TypeValidator2(new Integer(0)), new DateBorderValidator2())).
            createCommand());
        put("delete",
            new CommandDescriptionBuilder("delete").
                    setOptionsDescription(new OptionDescription("id").addValidator(new TypeValidator2(new Integer(0)))).
                    createCommand());
        put("update",
            new CommandDescriptionBuilder("update").
                    setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator2(new String())),
                            new OptionDescription("title").addValidator(new TypeValidator2(new String())),
                            new OptionDescription("year").addValidator(new TypeValidator2(new Integer(0)), new DateBorderValidator2()),
                            new OptionDescription("id").setMandatoryTrue().addValidator(new TypeValidator2(new Integer(0)))).
                    createCommand());
        put("search",
            new CommandDescriptionBuilder("search").
                    setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator2(new String())),
                            new OptionDescription("title").addValidator(new TypeValidator2(new String())),
                            new OptionDescription("year").addValidator(new TypeValidator2(new Integer(0)), new DateBorderValidator2())).
                    createCommand());
        put("list",
            new CommandDescriptionBuilder("list").
                    createCommand());
        put("help",
                new CommandDescriptionBuilder("help").
                        createCommand());
    }};

    public Map<String, CommandDescription> getCommandsDescriptionMap(){ //commandDescription
        return commandDescriptionMap;
    }

    public List<OptionDescription> getGlobalOptionsDescriptionList(){ //globalDescription
        return globalOptionsDescription;
    }
}
