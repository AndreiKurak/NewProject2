package common;

public interface Command<T> {

    ViewModel execute(T options, Object globalOptions);
}