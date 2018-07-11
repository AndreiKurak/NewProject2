package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.connectors.FileConnector;
import lib.command_options.ListCommandOptions;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        FileConnector<Book> fileConnector = new FileConnector<>("D:\\test");
        ViewModel viewModel = new ViewModel();
        ListCommandOptions listCommandOptions = (ListCommandOptions) options;

        try {
            List<Book> books = fileConnector.read();
            if (listCommandOptions.getAll() != null) {
                viewModel.model = books;
            }
            else{
                List<String> singleParameters = new ArrayList<>();
                for (Book book : books){
                    if (listCommandOptions.getAuthors() != null)
                        singleParameters.add(book.getAuthor());
                    if (listCommandOptions.getTitles() != null)
                        singleParameters.add(book.getTitle());
                    if (listCommandOptions.getYears() != null)
                        singleParameters.add(book.getYear());
                }
                viewModel.model = singleParameters;
            }
            viewModel.view = new ListView();
        }
        catch (Exception ex){
            viewModel.model = "List-command failed";
            viewModel.view = new ErrorView();
            System.out.println(ex.getClass());
        }

        return viewModel;
    }
}
