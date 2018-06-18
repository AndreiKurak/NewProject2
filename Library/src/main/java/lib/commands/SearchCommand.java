package lib.commands;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewModel;
import commonPac.views.ErrorView;
import lib.Book;
import commonPac.views.ListView;
import lib.MyLibrary;
import commonPac.OpenFileStream;

import java.lang.reflect.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(InputParameters inputParameters) {
        Map<String, String> input = inputParameters.getCommandOptions();
        OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        ViewModel viewModel = new ViewModel();

        SearchCommandOptions searchCommandOptions = new SearchCommandOptions();
        searchCommandOptions.setFields(inputParameters.getCommandOptions());

        try {
            List<Book> books = openFileStream.read();

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
