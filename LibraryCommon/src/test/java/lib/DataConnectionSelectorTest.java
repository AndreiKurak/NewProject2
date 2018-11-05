package lib;

import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionSelector;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataConnectionSelectorTest {

    private static class GlobalOptions extends lib.global_options.GlobalOptions{

        private String file;
        private String database;

        public String getFile() {
            return file;
        }

        public String getDatabase() {
            return database;
        }
    }

    @Test
    public void shouldChooseCorrectConnector(){
        GlobalOptions globalOptions = new GlobalOptions();
        globalOptions.database = "library";
        DataConnectionSelector selector = new DataConnectionSelector();
        assertThat(selector.select(globalOptions)).isInstanceOf(DataBaseConnector.class);
    }

    @Test
    public void shouldReturnNull(){
        GlobalOptions globalOptions = new GlobalOptions();
        DataConnectionSelector selector = new DataConnectionSelector();
        assertThat(selector.select(globalOptions)).isNull();
    }

}
