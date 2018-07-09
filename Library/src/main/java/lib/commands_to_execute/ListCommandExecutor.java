package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.ListCommandOptions;
import lib.commands.ListCommand;
import lib.global_options.GlobalOptions;

public class ListCommandExecutor implements ExecutableCommand {

    private ListCommandOptions listCommandOptions;
    private GlobalOptions globalOptions;

    public void setOptions(){
        listCommandOptions = new ListCommandOptions();
        globalOptions = new GlobalOptions();
    }

    public Object getOptions(){
        return listCommandOptions;
    }

    public Object getGlobalOptions() {
        return globalOptions;
    }

    public ViewModel setAndExecute(){
        return new ListCommand().execute(listCommandOptions, globalOptions);
    }
}
