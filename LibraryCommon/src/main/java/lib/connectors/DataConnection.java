package lib.connectors;

import lib.library_entities.Books;

public interface DataConnection {

    Books read();

    void write(Books myObject);
}
