package lib;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenFileStream {

    private static final String FILENAME = "D:\\library.ser";

    public ArrayList<Book> read() throws IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (ArrayList<Book>) ois.readObject();
    }

    public void write(List myObject) throws IOException{
        final FileOutputStream fos = new FileOutputStream(FILENAME);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
    }
}
