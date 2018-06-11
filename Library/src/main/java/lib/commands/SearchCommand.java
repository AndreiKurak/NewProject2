package lib.commands;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewModel;
import lib.Book;
import commonPac.views.ListView;
import lib.MyLibrary;
import commonPac.OpenFileStream;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(InputParameters inputParameters) {
        Map<String, String> input = inputParameters.getCommandOptions();
        OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        ViewModel viewModel = new ViewModel();

        try {
            List<Book> books = openFileStream.read();

            for (Book book : books){
                if (input.containsKey(AUTHOR))
                    if (!book.getAuthor().equals(input.get(AUTHOR)))
                       books.remove(book);
                if (input.containsKey(TITLE))
                    if (!book.getTitle().equals(input.get(TITLE)))
                        books.remove(book);
                if (input.containsKey(YEAR))
                    if (!book.getYear().equals(input.get(YEAR)))
                        books.remove(book);
            }
            viewModel.model = books;
            viewModel.view = new ListView();
        }
        catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viewModel;
    }
}
