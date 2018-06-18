package lib.commands;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewModel;
import commonPac.views.ErrorView;
import lib.Book;
import commonPac.views.ListView;
import lib.MyLibrary;
import commonPac.OpenFileStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "authors";
    private static final String TITLE = "titles";
    private static final String YEAR = "years";

    public ViewModel execute(InputParameters inputParameters) {
        OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        ViewModel viewModel = new ViewModel();

        try {
            List<Book> books = openFileStream.read();

            if (inputParameters.getCommandOptions().containsKey("all")) {
                viewModel.model = books;
            }
            else{
                List<String> singleParameters = new ArrayList<String>();

                for (Book book : books){
                    if (inputParameters.getCommandOptions().containsKey(AUTHOR))
                        singleParameters.add(book.getAuthor());
                    if (inputParameters.getCommandOptions().containsKey(TITLE))
                        singleParameters.add(book.getTitle());
                    if (inputParameters.getCommandOptions().containsKey(YEAR))
                        singleParameters.add(book.getYear());
                }
                viewModel.model = singleParameters;
            }
            viewModel.view = new ListView();
        }
        catch (Exception ex){
            viewModel.model = "List-command failed";
            viewModel.view = new ErrorView();
        }

        return viewModel;
    }
}
