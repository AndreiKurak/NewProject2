package common;

import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;
import common.parser.ParseException;
import common.parser.Parser;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    private Parser parser = new Parser();

    private static final List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", null, true));
        add(new OptionDescription("title", null, true));
        add(new OptionDescription("year", null, false));
    }};
    public static final class TestAdd implements Command{
        public ViewModel execute(Object options, Object globalOptions){ return null; }
    }
    public static final class TestAddCommandOptions {
        private String author;
        private String title;
        private String year;
    }
    public static final class TestGlobalOptions {
        private String file;
        private String database;
    }
    private static final List<CommandDescription> addList = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "", addOptions, TestAdd.class, TestAddCommandOptions.class));
    }};
    private static final List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file", null, false));
        add(new OptionDescription("database", null, false));
    }};

    @Test
    public void shouldParseCorrectCommandOptions() {
        String[] line ={"--file=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        CommandWithOptions command = parser.parse(line, globalOptions, addList, new TestGlobalOptions());
        try {
            Field author = command.getCommandOptions().getClass().getDeclaredField("author");
            author.setAccessible(true);
            assertThat("new author").isEqualTo((String) author.get(command.getCommandOptions()));

            Field year = command.getCommandOptions().getClass().getDeclaredField("year");
            year.setAccessible(true);
            assertThat("2019").isEqualTo((String) year.get(command.getCommandOptions()));
        }
        catch (Exception ex){
            throw new RuntimeException("Test failed", ex);
        }
    }

    @Test
    public void shouldParseCorrectGlobalOptions(){
        String[] line ={"--file=test1", "add", "--author=new"};

        CommandWithOptions command = parser.parse(line, globalOptions, addList, new TestGlobalOptions());
        try {
            Field file = command.getGlobalOptions().getClass().getDeclaredField("file");
            file.setAccessible(true);
            assertThat("test1").isEqualTo((String) file.get(command.getGlobalOptions()));
        }
        catch (Exception ex){
            throw new RuntimeException("Test failed", ex);
        }
    }

    @Test
    public void shouldParseCorrectCommand(){
        String[] line ={"--file=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        CommandWithOptions command = parser.parse(line, globalOptions, addList, new TestGlobalOptions());
        assertThat(TestAdd.class.getName()).isEqualTo(command.getCommand().getClass().getName());
    }

    @Test
    public void shouldThrowExceptionInformingAboutNullInputLine(){
        String[] line ={""};
        try {
            parser.parse(line, globalOptions, addList, new TestGlobalOptions());
        }
        catch (ParseException parseException){
            assertThat("Empty input line").isEqualTo(parseException.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionInformingAboutInputLineWithWrongCommand(){
        String[] line = {"--file=test", "edd", "--author=new", "author", "--title=World", "--year=2019"};
        try {
            parser.parse(line, globalOptions, addList, new TestGlobalOptions());
        }
        catch (ParseException parseException){
            assertThat("Wrong Command").isEqualTo(parseException.getMessage());
        }
    }
}
