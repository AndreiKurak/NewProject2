package commonPac.parser;

public class ParseException extends RuntimeException {

    ParseException(String error){
        super(error);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
