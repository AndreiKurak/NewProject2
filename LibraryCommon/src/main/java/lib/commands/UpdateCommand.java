package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import lib.command_options.UpdateCommandOptions;
import lib.global_options.GlobalOptions;
import lib.library_entities.Books;
import lib.library_entities.Book;
import lib.validators.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCommand implements Command<UpdateCommandOptions, GlobalOptions> {

    public ViewModel execute(UpdateCommandOptions options, GlobalOptions globalOptions) {
        ViewModel<String> viewModel = new ViewModel<>();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            viewModel.setModel(new Validator().validate(options, "update"));
            if (viewModel.getModel() == null) {
                DataConnection dbc = dcs.select(globalOptions);
                Books books = dbc.read();

                int id = Integer.valueOf(options.getId());
                Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
                books.update(id, book);
                dbc.write(books);

                viewModel.setModel("Update-command was performed");
                viewModel.setViewName("MessageView");
            }
            else
                viewModel.setViewName("ErrorView");
        }
        catch (Exception ex){
            viewModel.setModel("Update-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(UpdateCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }

        return viewModel;
    }
}
