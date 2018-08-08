package lib;

import common.views.MessageView;
import lib.command_options.AddCommandOptions;
import lib.commands.AddCommand;
import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionSelector;
import lib.global_options.GlobalOptions;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCommandTest {

    @Test
    public void shouldExecuteCommandSuccessfully(){
        DataConnectionSelector selector = mock(DataConnectionSelector.class);
        DataBaseConnector connector = mock(DataBaseConnector.class);

        when(selector.select(Matchers.<lib.global_options.GlobalOptions>any())).thenReturn(connector);
        when(connector.read()).thenReturn(new Books());

        assertThat(new AddCommand(selector).execute(new AddCommandOptions(), new GlobalOptions()).getView()).isInstanceOf(MessageView.class);
    }

    @Test
    public void shouldAddBookOnce(){
        DataConnectionSelector selector = mock(DataConnectionSelector.class);
        DataBaseConnector connector = mock(DataBaseConnector.class);
        Books books = mock(Books.class);

        when(selector.select(Matchers.<lib.global_options.GlobalOptions>any())).thenReturn(connector);
        when(connector.read()).thenReturn(books);

        new AddCommand(selector).execute(new AddCommandOptions(), new GlobalOptions());
        verify(books, times(1)).add(Matchers.<Book>anyObject());
    }
}
