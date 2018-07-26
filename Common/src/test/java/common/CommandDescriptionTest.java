package common;

import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandDescriptionTest {

    private static final List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", null, true));
        add(new OptionDescription("title", null, true));
        add(new OptionDescription("year", null, false));
    }};
    private static final CommandDescription DESCRIPTION = new CommandDescription("add", "add - command, that is used for adding new books to the library", addOptions, ParserTest.TestAdd.class, ParserTest.AddCommandOptions.class);

    @Test
    public void shouldCreateObjectOfCommandHavingClass(){
        assertThat(DESCRIPTION.createAndGetCommand().getClass()).isEqualTo(new ParserTest.TestAdd().getClass());
    }

    @Test
    public void shouldCreateObjectOfCommandOptionsHavingClass(){
        assertThat(DESCRIPTION.createAndGetCommandOptions().getClass()).isEqualTo(new ParserTest.AddCommandOptions().getClass());
    }
}
