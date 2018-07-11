package lib.connectors;

import common.DataConnection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileConnector<T> implements DataConnection {

    private String fileName = "library.ser";

    public FileConnector(String fileName) {
        this.fileName = fileName;
    }
         //Books
    public ArrayList<T> read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (ArrayList<T>) ois.readObject();
    }
                    //Books
    public void write(List myObject) throws IOException {
        final FileOutputStream fos = new FileOutputStream(fileName);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
    }
}
