package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.AddCommandOptions;
import lib.commands.AddCommand;
import lib.global_options.GlobalOptions;

public class AddCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new AddCommand().execute(options, globalOptions);
    }
}
