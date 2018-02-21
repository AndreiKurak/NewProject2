package commonPac;

public class ParseException extends Exception {

    private String error;

    ParseException(){ }

    public void emptyLine(){
        error = "Empty Input Line";
        System.out.println(error);
    }

    public void noCommand(){
        error = "Wrong command or missed";
        System.out.println(error);
    }
}
