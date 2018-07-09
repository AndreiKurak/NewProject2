package lib.commands;

import common.Command;
import common.DataBaseConnector;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import common.OpenFileStream;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(Object options, Object globalOptions) {
        Book book = new Book();
        ViewModel viewModel = new ViewModel();

        try {

            Field author = options.getClass().getDeclaredField(AUTHOR);
            author.setAccessible(true);
            book.setAuthor((String) author.get(options));
            Field title = options.getClass().getDeclaredField(TITLE);
            title.setAccessible(true);
            book.setTitle((String) title.get(options));
            Field year = options.getClass().getDeclaredField(YEAR);
            year.setAccessible(true);
            book.setYear((String) year.get(options));

            if (globalOptions.getClass().getDeclaredField(FILE1) != null) {
                Field file1 = globalOptions.getClass().getDeclaredField(FILE1);
                file1.setAccessible(true);
                OpenFileStream<Book> openFileStream = new OpenFileStream<>((String) file1.get(globalOptions));

                File f = new File((String) file1.get(globalOptions));
                long len = f.length();
                if (len != 0){
                    List<Book> books = openFileStream.read();
                    book.setId(books.size() + 1);
                    books.add(book);
                    openFileStream.write(books);
                }
                else {   //work with database
                    List<Book> books = new ArrayList<>();
                    book.setId(books.size() + 1);
                    books.add(book);
                    openFileStream.write(books);
                }
            }
            else {
                String query = "INSERT INTO doc_register.library (" + AUTHOR + ", " + TITLE + ", " + YEAR + ")" + "\n" +
                               " VALUES (" + author.get(options) + ", " + title.get(options) + ", " + year.get(options) + ");";    //char

                DataBaseConnector connector = new DataBaseConnector();
                connector.connect(query);
            }
            viewModel.model = "Add-command was performed";
            viewModel.view = new MessageView();
        }
        catch (Exception ex) {
            System.out.println(ex.getClass());
            viewModel.model = "Add-command failed";
            viewModel.view = new ErrorView();
        }
        /*catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }          */
        return viewModel;
    }
}
