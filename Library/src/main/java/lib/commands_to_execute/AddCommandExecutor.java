package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.commands.AddCommand;

public class AddCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new AddCommand().execute(options, globalOptions);
    }
}
