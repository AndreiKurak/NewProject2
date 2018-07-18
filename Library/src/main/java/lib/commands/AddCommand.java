package lib.commands;

import common.Command;
import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnection;
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

        ViewModel viewModel = new ViewModel();
        DataConnection dbc = new DataBaseConnector(); //
        Books books = dbc.read();
        try{
            books.add(options);
            dbc.write(books);

            viewModel.model = "Add-command was performed";
            viewModel.view = new MessageView();
        }
        catch (Exception ex){
            viewModel.model = "Add-command failed";
            viewModel.view = new ErrorView();
        }
        return viewModel;

        /*
        try {

            if (globalOptions.getClass().getDeclaredField(FILE1) != null) {
                Field file1 = globalOptions.getClass().getDeclaredField(FILE1);
                file1.setAccessible(true);
                FileConnector<Book> fileConnector = new FileConnector<>((String) file1.get(globalOptions));

                File f = new File((String) file1.get(globalOptions));
                long len = f.length();
                if (len != 0){
                    List<Book> books = new ArrayList<>();//List<Book> books = fileConnector.read();
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

        */
    }
}
