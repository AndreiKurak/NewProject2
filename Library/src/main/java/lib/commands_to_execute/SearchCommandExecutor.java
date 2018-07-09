package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.SearchCommandOptions;
import lib.commands.SearchCommand;
import lib.global_options.GlobalOptions;

public class SearchCommandExecutor implements ExecutableCommand {

    private SearchCommandOptions searchCommandOptions;
    private GlobalOptions globalOptions;

    public void setOptions(){
        searchCommandOptions = new SearchCommandOptions();
        globalOptions = new GlobalOptions();
    }

    public Object getOptions(){
        return searchCommandOptions;
    }

    public Object getGlobalOptions() {
        return globalOptions;
    }

    public ViewModel setAndExecute(){
        return new SearchCommand().execute(searchCommandOptions, globalOptions);
    }
}
