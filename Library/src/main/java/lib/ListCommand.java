package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand implements Command {

    private static final String AUTHOR = "authors";
    private static final String TITLE = "titles";
    private static final String YEAR = "years";

    public void execute(InputParameters inputParameters){
        OpenFileStream openFileStream = new OpenFileStream();

        try {
            List<Book> books = openFileStream.read();
            if (inputParameters.commandOptions.containsKey("all"))
                System.out.println(books);
            else
                for (Book book : books){
                    if (inputParameters.commandOptions.containsKey(AUTHOR))
                        System.out.println(book.author);
                    if (inputParameters.commandOptions.containsKey(TITLE))
                        System.out.println(book.title);
                    if (inputParameters.commandOptions.containsKey(YEAR))
                        System.out.println(book.year);
                }
        }
        catch (IOException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("list was performed");
    }
}
