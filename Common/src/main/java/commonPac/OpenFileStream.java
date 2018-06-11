package commonPac;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenFileStream<T> {   //заюзать generic-и и в общую часть?

    private String directory = "D:\\";  //путь должен указывать пользователь
    private String fileName = "library.ser";

    public OpenFileStream(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<T> read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(directory + fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (ArrayList<T>) ois.readObject();
    }

    public void write(List myObject) throws IOException {
        final FileOutputStream fos = new FileOutputStream(directory + fileName);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
    }
}
