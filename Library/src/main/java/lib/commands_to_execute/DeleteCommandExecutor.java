package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.commands.DeleteCommand;

public class DeleteCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new DeleteCommand().execute(options, globalOptions);
    }
}
