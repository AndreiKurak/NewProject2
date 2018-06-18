package commonPac;

import commonPac.descriptions.CommandDescription;
import commonPac.descriptions.OptionDescription;

import java.util.List;

public interface ApplicationDescriptor {

    List<CommandDescription> getCommandsList();

    List<OptionDescription> getGlobalOptionsList();

}
