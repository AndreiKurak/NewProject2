package console_app.commands;

import common.*;
import common.descriptions.CommandDescription;
import console_app.ConsoleLibraryDescriptor;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {

    public ViewModel execute(Object options, Object globalOptions){
        ViewModel<List> viewModel = new ViewModel<>();

        List<CommandDescription> commandsInfo = new ArrayList<>();
        ApplicationDescriptor libraryDescriptor = new ConsoleLibraryDescriptor();
        for (int i = 0; i< libraryDescriptor.getCommandsDescriptionList().size(); i++){
            commandsInfo.add(libraryDescriptor.getCommandsDescriptionList().get(i));
        }
        viewModel.setModel(commandsInfo);
        viewModel.setViewName("ListView");
        System.out.println("\n" + libraryDescriptor.getGlobalOptionsDescriptionList());

        return viewModel;
    }
}
