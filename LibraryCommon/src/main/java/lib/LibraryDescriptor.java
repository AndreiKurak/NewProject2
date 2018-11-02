package lib;

import common.descriptions.CommandDescription;
import common.descriptions.CommandDescriptionBuilder;
import common.descriptions.OptionDescription;
import lib.validators.DateBorderValidator;
import lib.validators.TypeValidator;

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
            setOptionsDescription(new OptionDescription("author").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                    new OptionDescription("title").setMandatoryTrue().addValidator(new TypeValidator(new String())),
                    new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
            createCommand());
        put("delete",
            new CommandDescriptionBuilder("delete").
                    setOptionsDescription(new OptionDescription("id").setMandatoryTrue().addValidator(new TypeValidator(new Integer(0)))).
                    createCommand());
        put("update",
            new CommandDescriptionBuilder("update").
                    setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                            new OptionDescription("title").addValidator(new TypeValidator(new String())),
                            new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator()),
                            new OptionDescription("id").setMandatoryTrue().addValidator(new TypeValidator(new Integer(0)))).
                    createCommand());
        put("search",
            new CommandDescriptionBuilder("search").
                    setOptionsDescription(new OptionDescription("author").addValidator(new TypeValidator(new String())),
                            new OptionDescription("title").addValidator(new TypeValidator(new String())),
                            new OptionDescription("year").addValidator(new TypeValidator(new Integer(0)), new DateBorderValidator())).
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
