package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCommand implements Command {

    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public void execute(InputParameters inputParameters){
        int id = Integer.valueOf(inputParameters.commandOptions.get("id")) - 1;

        try {
            BooksRegister booksRegister = OpenFileStream.read();
            if (inputParameters.commandOptions.containsKey(AUTHOR))
                booksRegister.books.get(id).author = inputParameters.commandOptions.get(AUTHOR);
            if (inputParameters.commandOptions.containsKey(TITLE))
                booksRegister.books.get(id).title = inputParameters.commandOptions.get(TITLE);
            if (inputParameters.commandOptions.containsKey(YEAR))
                booksRegister.books.get(id).year = inputParameters.commandOptions.get(YEAR);
            OpenFileStream.write(booksRegister);
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
