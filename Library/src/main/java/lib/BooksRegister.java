package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.Serializable;
import java.util.ArrayList;

public class BooksRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    ArrayList<Book> books = new ArrayList<Book>();

    public void realizeCommand(Command command, InputParameters inputParameters){
        command.execute(inputParameters);
    }
}
