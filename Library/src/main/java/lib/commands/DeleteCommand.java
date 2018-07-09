package lib.commands;

import common.Command;
import common.ViewModel;
import common.views.ErrorView;
import lib.Book;
import common.views.MessageView;
import common.OpenFileStream;

import java.lang.reflect.Field;
import java.util.List;

public class DeleteCommand implements Command {

    private static final String FILE1 = "file1";

    public ViewModel execute(Object options, Object globalOptions) {  //check
        //OpenFileStream<Book> openFileStream = new OpenFileStream<>(inputParameters.getGlobalOptions().get(FILE1));
        OpenFileStream<Book> openFileStream = new OpenFileStream<>("D:\\test");
        ViewModel viewModel = new ViewModel();

        try {
            Field field = options.getClass().getDeclaredField("id");
            field.setAccessible(true);
            int id = Integer.valueOf((String) field.get(options)) - 1;

            List<Book> books = openFileStream.read();
            books.remove(id);
            for (int i = id; i<books.size(); i++)
                books.get(id).setId(id + 1);
            openFileStream.write(books);

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
