package commonPac;

import commonPac.descriptions.CommandDescription;

import java.util.HashMap;
import java.util.Map;

public class InputParameters {

    private Map<String, String> globalOptions = new HashMap<String, String>();
    private CommandDescription command;
    private Map<String, String> commandOptions = new HashMap<String, String>();

    public Map<String, String> getGlobalOptions() {
        return globalOptions;
    }

    public void setGlobalOptions(Map<String, String> globalOptions) {
        this.globalOptions = globalOptions;
    }

    public CommandDescription getCommand() {
        return command;
    }

    public void setCommand(CommandDescription command) {
        this.command = command;
    }

    public Map<String, String> getCommandOptions() {
        return commandOptions;
    }

    public void setCommandOptions(Map<String, String> commandOptions) {
        this.commandOptions = commandOptions;
    }
}
