package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.UpdateCommandOptions;
import lib.global_options.GlobalOptions;

public class UpdateCommand implements Command<UpdateCommandOptions, GlobalOptions> {

    public ViewModel execute(UpdateCommandOptions options, GlobalOptions globalOptions) {
        ViewModel<String> viewModel = new ViewModel<>();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            int id = Integer.valueOf(options.getId()) - 1;
            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            books.update(id, book);
            dbc.write(books);
        }
        catch (Exception ex){
            viewModel.setModel("Update-command failed");
            viewModel.setViewName("ErrorView");
        }

        viewModel.setModel("Update-command was performed");
        viewModel.setViewName("MessageView");

        return viewModel;
    }
}
