package common.descriptions;

public class CommandDescriptionException extends RuntimeException {

    public CommandDescriptionException(String message) {
        super(message);
    }

    public CommandDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
