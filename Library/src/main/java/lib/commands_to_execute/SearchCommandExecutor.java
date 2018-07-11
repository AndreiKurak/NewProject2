package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.SearchCommandOptions;
import lib.commands.SearchCommand;
import lib.global_options.GlobalOptions;

public class SearchCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new SearchCommand().execute(options, globalOptions);
    }
}
