package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.command_options.SearchCommandOptions;

public class SearchCommand implements Command<SearchCommandOptions> {

    public ViewModel execute(SearchCommandOptions options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            viewModel.model = books.search(book);
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
