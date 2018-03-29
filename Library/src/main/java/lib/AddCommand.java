package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand implements Command {

    private static final String FILENAME = "D:\\library.ser";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public void execute(InputParameters inputParameters){
        Book book = new Book();
        OpenFileStream openFileStream = new OpenFileStream();

        book.author = inputParameters.commandOptions.get(AUTHOR);
        book.title = inputParameters.commandOptions.get(TITLE);
        if (inputParameters.commandOptions.containsKey(YEAR))
            book.year = inputParameters.commandOptions.get(YEAR);
        else
            book.year = "";

        try {
            File f = new File(FILENAME);
            long len = f.length();
            if (len != 0){
                List<Book> books = openFileStream.read();
                book.id = books.size() + 1;
                books.add(book);
                openFileStream.write(books);
            }
            else {
                List<Book> books = new ArrayList<Book>();
                book.id = books.size() + 1;
                books.add(book);
                openFileStream.write(books);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("add was performed");
    }
}
