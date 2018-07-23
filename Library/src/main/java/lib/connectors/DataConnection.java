package lib.connectors;

public interface DataConnection {

    String getName();

    Books read();

    void write(Books myObject);
}
