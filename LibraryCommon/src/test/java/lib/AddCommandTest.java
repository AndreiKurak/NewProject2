package lib;

import lib.command_options.AddCommandOptions;
import lib.commands.AddCommand;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionSelector;
import lib.global_options.GlobalOptions;
import lib.library_entities.Book;
import lib.library_entities.BooksFromDB;
import org.junit.Test;
import org.mockito.Matchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddCommandTest {

    @Test
    public void shouldExecuteCommandSuccessfully(){ //check
        DataConnectionSelector selector = mock(DataConnectionSelector.class);
        DataBaseConnector connector = mock(DataBaseConnector.class);

        when(selector.select(Matchers.any())).thenReturn(connector);
        when(connector.read()).thenReturn(new BooksFromDB());

        assertThat(new AddCommand(selector).execute(new AddCommandOptions(), new GlobalOptions()).getViewName()).isEqualTo("MessageView");
    }

    @Test
    public void shouldAddBookOnce(){    //check
        DataConnectionSelector selector = mock(DataConnectionSelector.class);
        DataBaseConnector connector = mock(DataBaseConnector.class);
        BooksFromDB books = mock(BooksFromDB.class);

        when(selector.select(Matchers.any())).thenReturn(connector);
        when(connector.read()).thenReturn(books);

        new AddCommand(selector).execute(new AddCommandOptions(), new GlobalOptions());
        verify(books, times(1)).add(Matchers.anyObject());
    }

}
