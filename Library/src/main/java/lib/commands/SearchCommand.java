package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import common.OpenFileStream;

import java.lang.reflect.Field;
import java.util.List;


public class SearchCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(Object options, Object globalOptions) {
        //Map<String, String> input = inputParameters.getCommandOptions();
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        OpenFileStream<Book> openFileStream = new OpenFileStream<>("D:\\test");
        ViewModel viewModel = new ViewModel();

        try {
            List<Book> books = openFileStream.read();
            Field author = options.getClass().getDeclaredField(AUTHOR);
            Field title = options.getClass().getDeclaredField(TITLE);
            Field year = options.getClass().getDeclaredField(YEAR);

            for (Book book : books){
                if (author != null)
                    if (!book.getAuthorField().equals(author)){
                       books.remove(book);
                       continue;
                    }
                if (title != null)
                    if (!book.getTitleField().equals(title)){
                        books.remove(book);
                        continue;
                    }
                if (year != null)
                    if (!book.getYearField().equals(year)){
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
