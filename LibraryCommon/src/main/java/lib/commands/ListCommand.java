package lib.commands;

import common.Command;
import common.ViewModel;
import lib.Book;
import lib.connectors.*;
import lib.command_options.ListCommandOptions;
import lib.global_options.GlobalOptions;
import lib.validators2.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand implements Command<ListCommandOptions, GlobalOptions> {

    public ViewModel execute(ListCommandOptions options, GlobalOptions globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            viewModel.setModel(new Validator().validate(options, "list"));
            if (viewModel.getModel() == null) {
                DataConnection dbc = dcs.select(globalOptions);
                Books books = new DataBaseConnector().read();//dbc.read();

                viewModel.setModel(books.list());
                viewModel.setViewName("ListView");
            }
            else
                viewModel.setViewName("ErrorView");
        }
        catch (Exception ex){
            viewModel.setModel("List-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
