package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.commands.HelpCommand;

public class HelpCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new HelpCommand().execute(null, null);
    }
    //no options?
}
