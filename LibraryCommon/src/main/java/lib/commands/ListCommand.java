package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.connectors.*;
import lib.command_options.ListCommandOptions;
import lib.global_options.GlobalOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand implements Command<ListCommandOptions, GlobalOptions> {

    public ViewModel execute(ListCommandOptions options, GlobalOptions globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            if (options.getAll() != null) {
                viewModel.setModel(books.list());
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
            viewModel.setModel(singleParameters);
            }
            viewModel.setViewName("ListView");
        }
        catch (Exception ex){
            viewModel.setModel("List-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
