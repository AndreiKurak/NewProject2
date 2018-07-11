package lib.commands;

import common.Command;
import lib.connectors.FileConnector;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import lib.command_options.SearchCommandOptions;

import java.util.List;


public class SearchCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {
        //Map<String, String> input = inputParameters.getCommandOptions();
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        FileConnector<Book> fileConnector = new FileConnector<>("D:\\test");
        ViewModel viewModel = new ViewModel();
        SearchCommandOptions searchCommandOptions = (SearchCommandOptions) options;

        try {
            List<Book> books = fileConnector.read();

            for (Book book : books){
                if (searchCommandOptions.getAuthor() != null)
                    if (!book.getAuthor().equals(searchCommandOptions.getAuthor())){
                       books.remove(book);
                       continue;
                    }
                if (searchCommandOptions.getTitle() != null)
                    if (!book.getTitle().equals(searchCommandOptions.getTitle())){
                        books.remove(book);
                        continue;
                    }
                if (searchCommandOptions.getYear() != null)
                    if (!book.getYear().equals(searchCommandOptions.getYear())){
                        books.remove(book);
                    }
            }
            viewModel.model = books;
            viewModel.view = new ListView();
            if (books == null){
                viewModel.model = "nothing found";
            }
        }
        catch (Exception ex){
            viewModel.model = "Search-command failed";
            viewModel.view = new ErrorView();            //java.util.ConcurrentModificationException
        }

        return viewModel;
    }
}
