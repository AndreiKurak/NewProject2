package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import lib.command_options.AddCommandOptions;
import lib.global_options.GlobalOptions;
import lib.library_entities.Books;
import lib.library_entities.Book;
import lib.validators.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand implements Command<AddCommandOptions, GlobalOptions> {

    private DataConnectionSelector dcs;

    public AddCommand(){
        dcs = new DataConnectionSelector();
    }

    public AddCommand(DataConnectionSelector selector){
        dcs = selector;
    }

    public ViewModel execute(AddCommandOptions options, GlobalOptions globalOptions) {

        ViewModel<String> viewModel = new ViewModel<>();

        try {
            viewModel.setModel(new Validator().validate(options, "add"));
            if (viewModel.getModel() == null) {
                DataConnection dbc = dcs.select(globalOptions);
                Books books = dbc.read();

                books.add(new Book(options.getAuthor(), options.getTitle(), options.getYear()));
                dbc.write(books);

                viewModel.setModel("Add-command was performed");
                viewModel.setViewName("MessageView");
            }
            else
                viewModel.setViewName("ErrorView");
        }
        catch (Exception ex){
            viewModel.setModel("Add-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(AddCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
