package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataConnection<T> {

    public T read();

    public void write(List myObject) throws IOException;
}
