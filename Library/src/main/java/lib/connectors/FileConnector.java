package lib.connectors;

import java.io.*;

public class FileConnector implements DataConnection {

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

    public void write(Books myObject) throws Exception {
        if (read().booksList.size() > myObject.booksList.size()){
            for (int i = 0; i<myObject.booksList.size() - 1; i++){
                if (myObject.booksList.get(i).getId() + 1 != myObject.booksList.get(i + 1).getId())
                    myObject.booksList.get(i + 1).setId(i + 2);
            }
        }
        final FileOutputStream fos = new FileOutputStream(fileName);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
    }
}
