package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import lib.Book;
import lib.command_options.SearchCommandOptions;
import lib.global_options.GlobalOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchCommand implements Command<SearchCommandOptions, GlobalOptions> {

    public ViewModel execute(SearchCommandOptions options, GlobalOptions globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            viewModel.setModel(books.search(book));
            viewModel.setViewName("ListView");
            if (viewModel.getModel() == null){
                viewModel.setModel("nothing found");
            }
        }
        catch (Exception ex){
            viewModel.setModel("Search-command failed:" + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(SearchCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
