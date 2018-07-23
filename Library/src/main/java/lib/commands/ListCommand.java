package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.connectors.*;
import lib.command_options.ListCommandOptions;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command<ListCommandOptions> {

    public ViewModel execute(ListCommandOptions options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            if (options.getAll() != null) {
                viewModel.model = books.list();
            }
            else{
                List<String> singleParameters = new ArrayList<>();
                for (Book book : books.list()){
                    if (options.getAuthors() != null)
                        singleParameters.add(book.getAuthor());
                    if (options.getTitles() != null)
                        singleParameters.add(book.getTitle());
                    if (options.getYears() != null)
                        singleParameters.add(book.getYear());
                }
            viewModel.model = singleParameters;
            }
            viewModel.view = new ListView();
        }
        catch (Exception ex){
            viewModel.model = "List-command failed";
            viewModel.view = new ErrorView();
        }
        return viewModel;
    }
}
