package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.commands.SearchCommand;

public class SearchCommandExecutor implements ExecutableCommand {

    public ViewModel setAndExecute(Object options, Object globalOptions){
        return new SearchCommand().execute(options, globalOptions);
    }
}
