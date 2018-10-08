package lib.commands;

import common.ApplicationExecution;
import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.AddCommandOptions;
import lib.global_options.GlobalOptions;

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

        try{
            DataConnection dbc = dcs.select(globalOptions);

            Books books = dbc.read();

            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            books.add(book);
            dbc.write(books);

            viewModel.setModel("Add-command was performed");
            viewModel.setViewName("MessageView");
        }
        catch (Exception ex){
            viewModel.setModel("Add-command failed: " + ex.getMessage());
            viewModel.setViewName("ErrorView");
            Logger.getLogger(AddCommand.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return viewModel;
    }
}
