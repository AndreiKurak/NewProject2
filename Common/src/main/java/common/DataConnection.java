package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataConnection<T> {

    public ArrayList<T> read() throws IOException, ClassNotFoundException;

    public void write(List myObject) throws IOException;
}
