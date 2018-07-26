package lib.connectors;

import java.io.*;

public class FileConnector implements DataConnection {

    private final String name = "file";
    private String fileName = "D:\\library.ser";

    public FileConnector(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
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
            if (read().booksList.size() > myObject.booksList.size()){
                for (int i = 0; i<myObject.booksList.size() - 1; i++){
                    if (myObject.booksList.get(i).getId() + 1 != myObject.booksList.get(i + 1).getId())
                        myObject.booksList.get(i + 1).setId(i + 2);
                }
            }
            oos.writeObject(myObject);
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during data loading into the file", ex);
        }
    }
}
