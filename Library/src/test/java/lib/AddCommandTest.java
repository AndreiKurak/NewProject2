package lib;

import common.views.MessageView;
import lib.commands.AddCommand;
import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionSelector;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AddCommandTest {

    private static class AddCommandOptions extends lib.command_options.AddCommandOptions {

        private String author = "Aleksey";
        private String title = "Mirror";
        private String year;

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }
    }

    private static class GlobalOptions extends lib.global_options.GlobalOptions{

        private String file;
        private String database = "library";

        public String getFile() {
            return file;
        }

        public String getDatabase() {
            return database;
        }
    }

    @Test
    public void shouldReturnViewModelWhereViewIsMessageView(){
        DataConnectionSelector selector = mock(DataConnectionSelector.class);
        DataBaseConnector connector = mock(DataBaseConnector.class);

        when(selector.select(Matchers.<lib.global_options.GlobalOptions>any())).thenReturn(connector);
        when(connector.read()).thenReturn(new Books());

        assertThat(new AddCommand(selector).execute(new AddCommandOptions(), new GlobalOptions()).view).isInstanceOf(MessageView.class);
    }
}
