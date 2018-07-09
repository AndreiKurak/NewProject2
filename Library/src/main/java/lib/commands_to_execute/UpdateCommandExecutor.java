package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.UpdateCommandOptions;
import lib.commands.UpdateCommand;
import lib.global_options.GlobalOptions;

public class UpdateCommandExecutor implements ExecutableCommand {

    private UpdateCommandOptions updateCommandOptions;
    private GlobalOptions globalOptions;

    public void setOptions(){
        updateCommandOptions = new UpdateCommandOptions();
        globalOptions = new GlobalOptions();
    }

    public Object getOptions(){
        return updateCommandOptions;
    }

    public Object getGlobalOptions() {
        return globalOptions;
    }

    public ViewModel setAndExecute(){
        return new UpdateCommand().execute(updateCommandOptions, globalOptions);
    }
}
