package lib.commands;

import common.Command;
import lib.connectors.DataBaseConnector;
import lib.connectors.FileConnector;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.AddCommandOptions;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        Book book = new Book();
        ViewModel viewModel = new ViewModel();
        AddCommandOptions addCommandOptions = (AddCommandOptions) options;

        book.setAuthor(addCommandOptions.getAuthor());
        book.setTitle(addCommandOptions.getTitle());
        book.setYear(addCommandOptions.getYear());

        try {

            if (globalOptions.getClass().getDeclaredField(FILE1) != null) {
                Field file1 = globalOptions.getClass().getDeclaredField(FILE1);
                file1.setAccessible(true);
                FileConnector<Book> fileConnector = new FileConnector<>((String) file1.get(globalOptions));

                File f = new File((String) file1.get(globalOptions));
                long len = f.length();
                if (len != 0){
                    List<Book> books = fileConnector.read();
                    book.setId(books.size() + 1);
                    books.add(book);
                    fileConnector.write(books);
                }
                else {   //work with database
                    List<Book> books = new ArrayList<>();
                    book.setId(books.size() + 1);
                    books.add(book);
                    fileConnector.write(books);
                }
            }
            else {
                String query = "";
            //    String query = "INSERT INTO doc_register.library (" + AUTHOR + ", " + TITLE + ", " + YEAR + ")" + "\n" +
              //                 " VALUES (" + author.get(options) + ", " + title.get(options) + ", " + year.get(options) + ");";    //char

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
