package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.AddCommandOptions;
import lib.commands.AddCommand;
import lib.global_options.GlobalOptions;

public class AddCommandExecutor implements ExecutableCommand {

    private AddCommandOptions addCommandOptions;
    private GlobalOptions globalOptions;

    public void setOptions(){
        addCommandOptions = new AddCommandOptions();
        globalOptions = new GlobalOptions();
    }

    public Object getOptions(){
        return addCommandOptions;
    }

    public Object getGlobalOptions() {
        return globalOptions;
    }

    public ViewModel setAndExecute(){
        return new AddCommand().execute(addCommandOptions, globalOptions);
    }
}
