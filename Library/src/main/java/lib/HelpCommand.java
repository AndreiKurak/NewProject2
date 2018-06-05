package lib;

import commonPac.*;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {

    private static final String HELP = "help";

    public ViewModel execute(InputParameters inputParameters){
        ViewModel viewModel = new ViewModel();

        if (inputParameters.globalOptions.containsKey(HELP)){
            List<CommandDescription> commandsInfo = new ArrayList<CommandDescription>();
            CommandEnumeration commandEnumeration = new CommandEnumeration();
            for (int i = 0; i< commandEnumeration.commands.size(); i++){
                commandsInfo.add(commandEnumeration.commands.get(i));
            }
            viewModel.model = commandsInfo;
            viewModel.view = new ListView();
            System.out.println("\n" + commandEnumeration.globalOptions);
        }
        return viewModel;
    }
}
