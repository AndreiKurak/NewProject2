package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import common.views.MessageView;
import lib.Book;
import common.views.ListView;
import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnection;
import lib.connectors.FileConnector;
import lib.command_options.ListCommandOptions;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();
        DataConnection dbc = new DataBaseConnector(); //
        Books books = dbc.read();
        try{
            viewModel.model = books.list(options);
            viewModel.view = new ListView();
        }
        catch (Exception ex){
            viewModel.model = "List-command failed";
            viewModel.view = new ErrorView();
        }
        return viewModel;
    }
}
