package lib;

import commonPac.Command;
import commonPac.InputParameters;
import commonPac.ViewModel;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand implements Command {

    public ViewModel execute(InputParameters inputParameters){
        OpenFileStream openFileStream = new OpenFileStream();
        ViewModel viewModel = new ViewModel();

        int id = Integer.valueOf(inputParameters.commandOptions.get("id")) - 1;

        try {
            List<Book> books = openFileStream.read();
            books.remove(id);
            for (int i = id; i<books.size(); i++)
                books.get(id).id--;
            openFileStream.write(books);
        }
        catch (IOException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

        viewModel.model = "Delete command was performed";
        viewModel.view = new MessageView();

        return viewModel;
    }
}
