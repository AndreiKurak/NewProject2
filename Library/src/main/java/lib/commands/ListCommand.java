package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.ListView;
import common.OpenFileStream;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "authors";
    private static final String TITLE = "titles";
    private static final String YEAR = "years";

    public ViewModel execute(Object options, Object globalOptions) {
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        OpenFileStream<Book> openFileStream = new OpenFileStream<>("D:\\test");
        ViewModel viewModel = new ViewModel();

        try {
            List<Book> books = openFileStream.read();
            if (options.getClass().getDeclaredField("all") != null) {
                viewModel.model = books;
            }
            else{
                List<String> singleParameters = new ArrayList<>();
                for (Book book : books){
                    if (options.getClass().getDeclaredField(AUTHOR) != null)
                        singleParameters.add(book.getAuthor());
                    if (options.getClass().getDeclaredField(TITLE) != null)
                        singleParameters.add(book.getTitle());
                    if (options.getClass().getDeclaredField(YEAR) != null)
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
