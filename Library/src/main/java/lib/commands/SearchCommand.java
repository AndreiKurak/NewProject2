package lib.commands;

import common.Command;
import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnection;
import lib.connectors.FileConnector;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.command_options.SearchCommandOptions;

import java.util.List;


public class SearchCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();
        DataConnection dbc = new DataBaseConnector(); //
        Books books = dbc.read();
        try{
            viewModel.model = books.search(options);
            viewModel.view = new ListView();
            if (viewModel.model == null){
                viewModel.model = "nothing found";
            }
        }
        catch (Exception ex){
            viewModel.model = "Search-command failed";
            viewModel.view = new ErrorView();            //java.util.ConcurrentModificationException
        }
        return viewModel;
    }
}
