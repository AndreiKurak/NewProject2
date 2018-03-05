package commonPac;

public class ParseException extends RuntimeException {

    ParseException(String error){

    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
