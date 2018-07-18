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
import lib.command_options.DeleteCommandOptions;

import java.util.List;

public class DeleteCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();
        DataConnection dbc = new DataBaseConnector(); //
        Books books = dbc.read();
        try{
            books.delete(options);
            dbc.write(books);

            viewModel.model = "Delete-command was performed";
            viewModel.view = new MessageView();
        }
        catch (Exception ex){
            viewModel.model = "Delete-command failed";
            viewModel.view = new ErrorView();
        }
        return viewModel;
    }
}
