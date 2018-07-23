package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import common.views.MessageView;
import lib.command_options.DeleteCommandOptions;

public class DeleteCommand implements Command<DeleteCommandOptions> {

    public ViewModel execute(DeleteCommandOptions options, Object globalOptions) {
        ViewModel viewModel = new ViewModel();

        DataConnectionSelector dcs = new DataConnectionSelector();
        try{
            DataConnection dbc = dcs.select(globalOptions);
            Books books = dbc.read();

            int bookId = Integer.valueOf(options.getId()) - 1;
            books.delete(bookId);
            dbc.write(books);

            viewModel.model = "Delete-command was performed";
            viewModel.view = new MessageView();
        }
        catch (Exception ex){
            viewModel.model = "Delete-command failed";
            viewModel.view = new ErrorView();
        }
        return viewModel;
    }
}
