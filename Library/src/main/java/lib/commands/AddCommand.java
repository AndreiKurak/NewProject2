package lib.commands;

import common.Command;
import lib.connectors.*;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.AddCommandOptions;
import lib.global_options.GlobalOptions;

public class AddCommand implements Command<AddCommandOptions, GlobalOptions> {

    private DataConnectionSelector dcs;

        public AddCommand(){
            dcs = new DataConnectionSelector();
        }

        public AddCommand(DataConnectionSelector selector){
            dcs = selector;
        }

    public ViewModel execute(AddCommandOptions options, GlobalOptions globalOptions) {

        ViewModel viewModel = new ViewModel();

        try{
            DataConnection dbc = dcs.select(globalOptions);

            Books books = dbc.read();

            Book book = new Book(options.getAuthor(), options.getTitle(), options.getYear());
            books.add(book);
            dbc.write(books);

            viewModel.model = "Add-command was performed";
            viewModel.view = new MessageView();
        }
        catch (Exception ex){
            viewModel.model = "Add-command failed";
            viewModel.view = new ErrorView();
        }
        return viewModel;
    }
}
