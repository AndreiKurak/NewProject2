package lib.connectors;

public interface DataConnection {

    Books read();

    void write(Books myObject);
}
