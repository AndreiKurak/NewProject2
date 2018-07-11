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

    public Books read(){
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return (Books) ois.readObject();
        }
        catch (Exception ex){
            return null; //
        }
    }
                    //Books
    public void write(List myObject) throws IOException {
        final FileOutputStream fos = new FileOutputStream(fileName);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
    }

    //Books
    public ArrayList<T> read(String h) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (ArrayList<T>) ois.readObject();
    }
}
