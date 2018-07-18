package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.commands.UpdateCommand;

public class UpdateCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new UpdateCommand().execute(options, globalOptions);
    }
}
