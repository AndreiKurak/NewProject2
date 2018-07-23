package lib.connectors;

public class DataConnectionException extends RuntimeException {

    public DataConnectionException(String message) {
        super(message);
    }

    public DataConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
