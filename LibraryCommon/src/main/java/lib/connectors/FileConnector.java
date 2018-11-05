package lib.connectors;

import lib.library_entities.Books;

import java.io.*;

public class FileConnector implements DataConnection {

    private String fileName = "D:\\library.ser";

    public FileConnector() { }

    public FileConnector(String fileName) {
        this.fileName = fileName;
    }

    public Books read(){
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Books) ois.readObject();
        }
        catch (Exception ex){
            throw new DataConnectionException("Data reading from file failed", ex);
        }
    }

    public void write(Books myObject) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(myObject);
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during data loading into the file", ex);
        }
    }
}
