package common;

public class CommandWithOptions {

    private Command command;
    private Object globalOptions;
    private Object commandOptions;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Object getCommandOptions() {
        return commandOptions;
    }

    public void setCommandOptions(Object commandOptions){
        this.commandOptions = commandOptions;
    }

    public Object getGlobalOptions() {
        return globalOptions;
    }

    public void setGlobalOptions(Object globalOptions) {
        this.globalOptions = globalOptions;
    }
}
