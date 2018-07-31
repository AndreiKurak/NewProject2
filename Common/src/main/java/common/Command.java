package common;

public interface Command<CommandOptions, GlobalOptions> {

    ViewModel execute(CommandOptions commandOptions, GlobalOptions globalOptions);
}