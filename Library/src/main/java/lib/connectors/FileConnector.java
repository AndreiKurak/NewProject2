package lib.connectors;

import java.io.*;

public class FileConnector implements DataConnection {

    private final String name = "file";
    private String fileName = "library.ser";

    public FileConnector(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public Books read(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return (Books) ois.readObject();
        }
        catch (Exception ex){
            throw new DataConnectionException("Data reading from file failed");
        }
        finally {
            try { fis.close(); } catch (Exception ex) { /*...*/ }
            try { ois.close(); } catch (Exception ex) { /*...*/ }
        }
    }

    public void write(Books myObject) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            if (read().booksList.size() > myObject.booksList.size()){
                for (int i = 0; i<myObject.booksList.size() - 1; i++){
                    if (myObject.booksList.get(i).getId() + 1 != myObject.booksList.get(i + 1).getId())
                        myObject.booksList.get(i + 1).setId(i + 2);
                }
            }
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(myObject);
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during data loading into the file");
        }
        finally {
            try { fos.close(); } catch (Exception ex) { /*...*/ }
            try { oos.close(); } catch (Exception ex) { /*...*/ }
        }
    }
}
