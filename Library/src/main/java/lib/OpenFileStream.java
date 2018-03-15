package lib;

import java.io.*;

public class OpenFileStream {

    private static final String FILENAME = "D:\\library.ser";

    public static BooksRegister read() throws IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (BooksRegister) ois.readObject();
    }

    public static void write(BooksRegister myObject) throws IOException{
        final FileOutputStream fos = new FileOutputStream(FILENAME);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
    }
}
