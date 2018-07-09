package common;

public interface ExecutableCommand {

    void setOptions();

    Object getOptions();

    Object getGlobalOptions();

    ViewModel setAndExecute();
}
