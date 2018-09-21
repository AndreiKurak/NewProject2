package common.parser;

public class ParseException extends RuntimeException {

    public ParseException(String error){
        super(error);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
