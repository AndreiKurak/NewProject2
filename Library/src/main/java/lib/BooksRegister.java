package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class BooksRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    ArrayList<Map<String, String>> books = new ArrayList<Map<String, String>>();

    public void realizeCommand(Command command, InputParameters inputParameters){
        command.method1(inputParameters, books);
    }
}
