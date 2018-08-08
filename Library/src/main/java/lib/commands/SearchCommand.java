package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.command_options.SearchCommandOptions;
import lib.global_options.GlobalOptions;

public class SearchCommand implements Command<SearchCommandOptions, GlobalOptions> {

    public ViewModel execute(SearchCommandOptions options, GlobalOptions globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            viewModel.setModel(books.search(book));
            viewModel.setView(new ListView());
            if (viewModel.getModel() == null){
                viewModel.setModel("nothing found");
            }
        }
        catch (Exception ex){
            viewModel.setModel("Search-command failed");
            viewModel.setView(new ErrorView());            //java.util.ConcurrentModificationException
        }
        return viewModel;
    }
}
