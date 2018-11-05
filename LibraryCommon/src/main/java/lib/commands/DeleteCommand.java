package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import lib.command_options.DeleteCommandOptions;
import lib.global_options.GlobalOptions;
import lib.library_entities.Books;
import lib.validators.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand implements Command<DeleteCommandOptions, GlobalOptions> {

    public ViewModel execute(DeleteCommandOptions options, GlobalOptions globalOptions) {
        ViewModel<String> viewModel = new ViewModel<>();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            viewModel.setModel(new Validator().validate(options, "delete"));
            if (viewModel.getModel() == null) {
                DataConnection dbc = dcs.select(globalOptions);
                Books books = dbc.read();

                int bookId = Integer.valueOf(options.getId());
                books.delete(bookId);
                dbc.write(books);

                viewModel.setModel("Delete-command was performed");
                viewModel.setViewName("MessageView");
            }
            else
                viewModel.setViewName("ErrorView");
        }
        catch (Exception ex){
            viewModel.setModel("Delete-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(DeleteCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
