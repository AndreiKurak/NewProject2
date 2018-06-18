package lib.commands;

import commonPac.*;
import commonPac.descriptions.CommandDescription;
import lib.LibraryDescriptor;
import commonPac.views.ListView;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {

    public ViewModel execute(InputParameters inputParameters){  //check
        ViewModel viewModel = new ViewModel();

        List<CommandDescription> commandsInfo = new ArrayList<>();
        LibraryDescriptor libraryDescriptor = new LibraryDescriptor();
        for (int i = 0; i< libraryDescriptor.getCommandsList().size(); i++){
            commandsInfo.add(libraryDescriptor.getCommandsList().get(i));
        }
        viewModel.model = commandsInfo;
        viewModel.view = new ListView();
        System.out.println("\n" + libraryDescriptor.getGlobalOptionsList());

        return viewModel;
    }
}
