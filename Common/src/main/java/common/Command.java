package common;

public interface Command {

    public ViewModel execute(Object options, Object globalOptions);
}