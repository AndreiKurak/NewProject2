package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.command_options.DeleteCommandOptions;
import lib.commands.DeleteCommand;
import lib.global_options.GlobalOptions;

public class DeleteCommandExecutor implements ExecutableCommand {

    private DeleteCommandOptions deleteCommandOptions;
    private GlobalOptions globalOptions;

    public void setOptions(){
        deleteCommandOptions = new DeleteCommandOptions();
        globalOptions = new GlobalOptions();
    }

    public Object getOptions(){
        return deleteCommandOptions;
    }

    public Object getGlobalOptions() {
        return globalOptions;
    }

    public ViewModel setAndExecute(){
        return new DeleteCommand().execute(deleteCommandOptions, deleteCommandOptions);
    }
}
