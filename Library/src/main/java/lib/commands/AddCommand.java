package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.AddCommandOptions;
import lib.global_options.GlobalOptions;

public class AddCommand implements Command<AddCommandOptions, GlobalOptions> {

    public ViewModel execute(AddCommandOptions options, GlobalOptions globalOptions) {

        ViewModel viewModel = new ViewModel();
        DataConnectionSelector dcs = new DataConnectionSelector();
         //

        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            books.add(book);
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
