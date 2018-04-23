package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCommand implements Command {

    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public void execute(InputParameters inputParameters){
        OpenFileStream openFileStream = new OpenFileStream();
        int id = Integer.valueOf(inputParameters.commandOptions.get("id")) - 1;

        try {
            List<Book> books = openFileStream.read();
            if (inputParameters.commandOptions.containsKey(AUTHOR))
                books.get(id).author = inputParameters.commandOptions.get(AUTHOR);
            if (inputParameters.commandOptions.containsKey(TITLE))
                books.get(id).title = inputParameters.commandOptions.get(TITLE);
            if (inputParameters.commandOptions.containsKey(YEAR))
                books.get(id).year = inputParameters.commandOptions.get(YEAR);
            openFileStream.write(books);
            //подумать насчёт нулевых значений
        }
        catch (IOException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("update was performed");
    }
}