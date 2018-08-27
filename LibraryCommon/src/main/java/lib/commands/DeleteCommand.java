package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import common.views.MessageView;
import lib.command_options.DeleteCommandOptions;
import lib.global_options.GlobalOptions;

public class DeleteCommand implements Command<DeleteCommandOptions, GlobalOptions> {

    public ViewModel execute(DeleteCommandOptions options, GlobalOptions globalOptions) {
        ViewModel<String> viewModel = new ViewModel<>();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            int bookId = Integer.valueOf(options.getId()) - 1;
            books.delete(bookId);
            dbc.write(books);

            viewModel.setModel("Delete-command was performed");
            viewModel.setView(new MessageView());
        }
        catch (Exception ex){
            viewModel.setModel("Delete-command failed");
            viewModel.setView(new ErrorView());
        }
        return viewModel;
    }
}
