package lib.commands;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewModel;
import lib.Book;
import commonPac.views.MessageView;
import lib.MyLibrary;
import commonPac.OpenFileStream;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(InputParameters inputParameters) {
        OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        int id = Integer.valueOf(inputParameters.getCommandOptions().get("id")) - 1;
        ViewModel viewModel = new ViewModel();

        try {
            List<Book> books = openFileStream.read();
            if (inputParameters.getCommandOptions().containsKey(AUTHOR))
                books.get(id).setAuthor(inputParameters.getCommandOptions().get(AUTHOR));
            if (inputParameters.getCommandOptions().containsKey(TITLE))
                books.get(id).setTitle(inputParameters.getCommandOptions().get(TITLE));
            if (inputParameters.getCommandOptions().containsKey(YEAR))
                books.get(id).setYear(inputParameters.getCommandOptions().get(YEAR));
            openFileStream.write(books);
        }
        catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

        viewModel.model = "Update command was performed";
        viewModel.view = new MessageView();

        return viewModel;
    }
}
