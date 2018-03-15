package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand implements Command {

    public void execute(InputParameters inputParameters){
        int id = Integer.valueOf(inputParameters.commandOptions.get("id")) - 1;

        try {
            BooksRegister booksRegister = OpenFileStream.read();
            booksRegister.books.remove(id);
            for (int i = id; i<booksRegister.books.size(); i++)
                booksRegister.books.get(id).id--;
            OpenFileStream.write(booksRegister);
        }
        catch (IOException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("delete was performed");
    }
}
