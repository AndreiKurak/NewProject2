package lib;

import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseConnectorTest {

    @Test
    public void shouldLoadDataFromDatabaseCorrectly(){
        DataBaseConnector dbc = new DataBaseConnector();
        Books books = dbc.read();
        assertThat(books.list().get(0).getAuthor()).isEqualTo("auth");
        assertThat(books.list().get(0).getYear()).isEqualTo("1678");
    }

}
