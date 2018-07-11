package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.ListCommandOptions;
import lib.commands.ListCommand;
import lib.global_options.GlobalOptions;

public class ListCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new ListCommand().execute(options, globalOptions);
    }
}
