package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.UpdateCommandOptions;
import lib.commands.UpdateCommand;
import lib.global_options.GlobalOptions;

public class UpdateCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new UpdateCommand().execute(options, globalOptions);
    }
}
