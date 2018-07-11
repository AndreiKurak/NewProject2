package lib.commands;

import common.Command;
import lib.connectors.FileConnector;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.DeleteCommandOptions;

import java.util.List;

public class DeleteCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {  //check
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        FileConnector<Book> fileConnector = new FileConnector<>("D:\\test");
        ViewModel viewModel = new ViewModel();
        DeleteCommandOptions deleteCommandOptions = (DeleteCommandOptions) options;

        int id = Integer.valueOf(deleteCommandOptions.getId());

        try {
            List<Book> books = fileConnector.read();
            books.remove(id);
            for (int i = id; i<books.size(); i++)
                books.get(id).setId(id + 1);
            fileConnector.write(books);

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
