package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import common.OpenFileStream;

import java.lang.reflect.Field;
import java.util.List;

public class UpdateCommand implements Command {

    private static final String FILE1 = "file1";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String YEAR = "year";

    public ViewModel execute(Object options, Object globalOptions) {
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        OpenFileStream<Book> openFileStream = new OpenFileStream<>("D:\\test");
        ViewModel viewModel = new ViewModel();

        try {
            Field field = options.getClass().getDeclaredField("id");
            field.setAccessible(true);
            int id = Integer.valueOf((String) field.get(options)) - 1;

            List<Book> books = openFileStream.read();
            if (options.getClass().getDeclaredField(AUTHOR) != null){
                Field author = options.getClass().getDeclaredField(AUTHOR);
                author.setAccessible(true);
                books.get(id).setAuthor((String) author.get(options));
            }
            if (options.getClass().getDeclaredField(TITLE) != null){
                Field title = options.getClass().getDeclaredField(TITLE);
                title.setAccessible(true);
                books.get(id).setTitle((String) title.get(options));
            }
            if (options.getClass().getDeclaredField(YEAR) != null){
                Field year = options.getClass().getDeclaredField(YEAR);
                year.setAccessible(true);
                books.get(id).setYear((String) year.get(options));
            }
            openFileStream.write(books);
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
