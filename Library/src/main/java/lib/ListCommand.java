package lib;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand implements Command {

    private static final String AUTHOR = "authors";
    private static final String TITLE = "titles";
    private static final String YEAR = "years";

    public void execute(InputParameters inputParameters, ViewController controller){
        OpenFileStream openFileStream = new OpenFileStream();

        try {
            List<Book> books = openFileStream.read();

            if (inputParameters.commandOptions.containsKey("all"))          {
                controller.model = books;
            }
            else{
                List<String> singleParameters = new ArrayList<String>();

                for (Book book : books){
                    if (inputParameters.commandOptions.containsKey(AUTHOR))
                        singleParameters.add(book.author);
                    if (inputParameters.commandOptions.containsKey(TITLE))
                        singleParameters.add(book.title);
                    if (inputParameters.commandOptions.containsKey(YEAR))
                        singleParameters.add(book.year);
                }
                controller.model = singleParameters;
            }
        }
        catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
