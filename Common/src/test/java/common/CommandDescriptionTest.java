package common;

import common.descriptions.CommandDescription;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandDescriptionTest {

    public static final class TestAddCommandOptions {
        private String author;
        private String title;
        private String year;
    }
    public static final class TestAdd implements Command{
        public ViewModel execute(Object options, Object globalOptions){ return null; }
    }

    @Test
    public void shouldCreateObjectOfCommandHavingClass(){
        CommandDescription description = new CommandDescription("", "", null, TestAdd.class, null);
        assertThat(description.createAndGetCommand()).isInstanceOf(TestAdd.class);
    }

    @Test
    public void shouldCreateObjectOfCommandOptionsHavingClass(){
        CommandDescription description = new CommandDescription("", "", null, null, TestAddCommandOptions.class);
        assertThat(description.createAndGetCommandOptions()).isInstanceOf(TestAddCommandOptions.class);
    }
}
