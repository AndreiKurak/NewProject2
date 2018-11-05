package lib.commands;

import common.Command;
import common.ViewModel;
import lib.command_options.EmptyCommandOptions;
import lib.connectors.*;
import lib.global_options.GlobalOptions;
import lib.library_entities.Books;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand implements Command<EmptyCommandOptions, GlobalOptions> {

    public ViewModel execute(EmptyCommandOptions options, GlobalOptions globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            if (viewModel.getModel() == null) {
                DataConnection dbc = dcs.select(globalOptions);
                Books books = new DataBaseConnector().read();//dbc.read();

                viewModel.setModel(books.list());
                viewModel.setViewName("ListView");
            }
        }
        catch (Exception ex){
            viewModel.setModel("List-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
