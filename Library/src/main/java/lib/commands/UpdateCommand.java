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
import lib.command_options.UpdateCommandOptions;

import java.util.List;

public class UpdateCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();
        DataConnection dbc = new DataBaseConnector(); //
        Books books = dbc.read();
        try{
            books.update(options);
            dbc.write(books);
        }
        catch (Exception ex){
            viewModel.model = "Update-command failed";
            viewModel.view = new ErrorView();
        }

        viewModel.model = "Update-command was performed";
        viewModel.view = new MessageView();

        return viewModel;
    }
}
