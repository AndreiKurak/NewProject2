package lib;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchCommand implements Command {

    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public void execute(InputParameters inputParameters, ViewController controller){
        Map<String, String> input = inputParameters.commandOptions;
        OpenFileStream openFileStream = new OpenFileStream();

        try {
            List<Book> books = openFileStream.read();

            for (Book book : books){
                if (input.containsKey(AUTHOR))
                    if (!book.author.equals(input.get(AUTHOR)))
                       books.remove(book);
                if (input.containsKey(TITLE))
                    if (!book.title.equals(input.get(TITLE)))
                        books.remove(book);
                if (input.containsKey(YEAR))
                    if (!book.year.equals(input.get(YEAR)))
                        books.remove(book);
            }
            controller.model = books;
        }
        catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
