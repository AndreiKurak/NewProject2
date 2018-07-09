package common;

import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;

import java.util.List;

public interface ApplicationDescriptor {

    List<CommandDescription> getCommandsList();

    List<OptionDescription> getGlobalOptionsList();

}
