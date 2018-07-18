package lib.connectors;

public interface DataConnection {

    public Books read();

    public void write(Books myObject) throws Exception;
}
