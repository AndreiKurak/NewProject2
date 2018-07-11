package lib.commands;

import common.Command;
import lib.connectors.FileConnector;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import lib.command_options.UpdateCommandOptions;

import java.util.List;

public class UpdateCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        FileConnector<Book> fileConnector = new FileConnector<>("D:\\test");
        ViewModel viewModel = new ViewModel();
        UpdateCommandOptions updateCommandOptions = (UpdateCommandOptions) options;

        int id = Integer.valueOf(updateCommandOptions.getId());

        try {
            List<Book> books = fileConnector.read();
            if (updateCommandOptions.getAuthor() != null){
                books.get(id).setAuthor(updateCommandOptions.getAuthor());
            }
            if (updateCommandOptions.getTitle() != null){
                books.get(id).setTitle(updateCommandOptions.getTitle());
            }
            if (updateCommandOptions.getYear() != null){
                books.get(id).setYear(updateCommandOptions.getYear());
            }
            fileConnector.write(books);
        }
        catch (Exception ex){
            viewModel.model = "Update-command failed";
            viewModel.view = new ErrorView();
        }

        viewModel.model = "Update-command was performed";
        viewModel.view = new MessageView();

        return viewModel;
    }
}
