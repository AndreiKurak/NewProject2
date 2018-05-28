package lib;

import commonPac.Command;
import commonPac.CommandDescription;
import commonPac.InputParameters;
import commonPac.ViewController;

import java.util.ArrayList;
import java.util.List;

public class GlobalOptionsCommand implements Command {

    private static final String HELP = "help";
    private static final String FILE = "file";

    public void execute(InputParameters inputParameters, ViewController controller){
        if (inputParameters.globalOptions.containsKey(HELP)){
            List<CommandDescription> commandsInfo = new ArrayList<CommandDescription>();
            CommandEnumeration commandEnumeration = new CommandEnumeration();
            for (int i = 0; i< commandEnumeration.commands.size(); i++){
                commandsInfo.add(commandEnumeration.commands.get(i));
            }
            controller.model = commandsInfo;
            System.out.println("\n" + commandEnumeration.globalOptions);
        }
        if (inputParameters.globalOptions.containsKey(FILE)){

        }
    }
}
