package lib.commands;

import commonPac.*;
import commonPac.descriptions.CommandDescription;
import lib.CommandEnumeration;
import commonPac.views.ListView;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {

    public ViewModel execute(InputParameters inputParameters){  //check
        ViewModel viewModel = new ViewModel();

        List<CommandDescription> commandsInfo = new ArrayList<>();
        CommandEnumeration commandEnumeration = new CommandEnumeration();
        for (int i = 0; i< commandEnumeration.commands.size(); i++){
            commandsInfo.add(commandEnumeration.commands.get(i));
        }
        viewModel.model = commandsInfo;
        viewModel.view = new ListView();
        System.out.println("\n" + commandEnumeration.globalOptions);

        return viewModel;
    }
}
