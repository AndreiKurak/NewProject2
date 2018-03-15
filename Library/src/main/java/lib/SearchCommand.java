package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchCommand implements Command {

    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public void execute(InputParameters inputParameters){
        Map<String, String> input = inputParameters.commandOptions;

        try {
            BooksRegister booksRegister = OpenFileStream.read();
            boolean contains = true;

            for (Book book : booksRegister.books){
                if (input.containsKey(AUTHOR))
                    if (!book.author.equals(input.get(AUTHOR)))
                        contains = false;
                if (input.containsKey(TITLE))
                    if (!book.author.equals(input.get(TITLE)))
                    contains = false;
                if (input.containsKey(YEAR))
                    if (!book.author.equals(input.get(YEAR)))
                    contains = false;
                if (contains){
                    System.out.println(book);
                    break;
                }
            }
            //recheck
        }
        catch (IOException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("search was performed");
    }
}
