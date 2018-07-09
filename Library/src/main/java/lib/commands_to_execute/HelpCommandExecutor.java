package lib.commands_to_execute;

import common.ExecutableCommand;
import common.ViewModel;
import lib.commands.HelpCommand;

public class HelpCommandExecutor implements ExecutableCommand {

    public void setOptions(){    }

    public Object getOptions(){
        return null;
    }

    public Object getGlobalOptions() {
        return null;
    }

    public ViewModel setAndExecute(){
        return new HelpCommand().execute(null, null);
    }
    //no options?
}
