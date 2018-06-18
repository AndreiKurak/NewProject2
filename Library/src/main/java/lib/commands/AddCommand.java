package lib.commands;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewModel;
import commonPac.views.ErrorView;
import lib.Book;
import commonPac.views.MessageView;
import commonPac.OpenFileStream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(InputParameters inputParameters) {
        Book book = new Book();
        OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        ViewModel viewModel = new ViewModel();

        book.setAuthor(inputParameters.getCommandOptions().get(AUTHOR));
        book.setTitle(inputParameters.getCommandOptions().get(TITLE));
        if (inputParameters.getCommandOptions().containsKey(YEAR))
            book.setYear(inputParameters.getCommandOptions().get(YEAR));
        else
            book.setYear("");

        try {
            File f = new File(inputParameters.getGlobalOptions().get(FILE1));
            long len = f.length();
            if (len != 0){
                List<Book> books = openFileStream.read();
                book.setId(books.size() + 1);
                books.add(book);
                openFileStream.write(books);
            }
            else {
                List<Book> books = new ArrayList<Book>();
                book.setId(books.size() + 1);
                books.add(book);
                openFileStream.write(books);
            }

            viewModel.model = "Add-command was performed";
            viewModel.view = new MessageView();
        }
        catch (Exception ex) {
            viewModel.model = "Add-command failed";
            viewModel.view = new ErrorView();
        }
        /*catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }        */

        return viewModel;
    }
}
