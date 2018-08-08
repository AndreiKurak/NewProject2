package common;

public class CommandWithOptions<CommandOptions, GlobalOptions> {

    private Command command;
    private GlobalOptions globalOptions;
    private CommandOptions commandOptions;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public CommandOptions getCommandOptions() {
        return commandOptions;
    }

    public void setCommandOptions(CommandOptions commandOptions){
        this.commandOptions = commandOptions;
    }

    public GlobalOptions getGlobalOptions() {
        return globalOptions;
    }

    public void setGlobalOptions(GlobalOptions globalOptions) {
        this.globalOptions = globalOptions;
    }
}
