package common;

import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;
import common.parser.ParseException;
import common.parser.Parser;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

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
    private static class TestAdd implements Command{
        public ViewModel execute(Object options, Object globalOptions){ return null; }
    }
    private static class TestExecutor implements ExecutableCommand{
        public void setOptions() {}
        public Object getOptions() { return null; }
        public Object getGlobalOptions() { return null; }
        public ViewModel setAndExecute() { return null; }
    }
    private static final List<CommandDescription> addList = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "add - command, that is used for adding new books to the library", addOptions, new TestExecutor()));
    }};
    private static final List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file1", null, true));
        add(new OptionDescription("file2", null, false));
        add(new OptionDescription("help", null, false));
    }};

    @Test
    public void parseCorrectOptions() {
        String[] line ={"--file1=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        ExecutableCommand command = parser.parse(line, globalOptions, addList);
        try {
            Field field = command.getOptions().getClass().getDeclaredField("author");
            field.setAccessible(true);
            assertThat("new author").isEqualTo((String) field.get(command.getOptions()));
        }
        catch (Exception ex){
            System.out.println(ex.getClass());
        }
    }

    @Test
    public void parseCorrectGlobalOptions(){
        String[] line ={"--file1=test1", "--file2=test2", "add", "--author=new"};

        ExecutableCommand command = parser.parse(line, globalOptions, addList);
        //assertThat("test1").isEqualTo(inputParameters.getGlobalOptions().get("file1"));
        //assertThat("test2").isEqualTo(inputParameters.getGlobalOptions().get("file2"));
    }

    @Test
    public void parseCorrectCommand(){
        String[] line ={"--file1=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        ExecutableCommand command = parser.parse(line, globalOptions, addList);
        //assertThat("add").isEqualTo(inputParameters.getCommand().getName());
    }

    @Test//(expected = ParseException.class)
    public void nullInputLine(){
        String[] line ={""};
        try {
            parser.parse(line, globalOptions, addList);
            fail("ParseException was expected");
        }
        catch (ParseException parseException){
            assertThat("Empty input line").isEqualTo(parseException.getMessage());
        }
    }

    @Test
    public void InputLineWithWrongCommand(){
        String[] line = {"--file1=test", "edd", "--author=new", "author", "--title=World", "--year=2019"};
        try {
            parser.parse(line, globalOptions, addList);
            fail("ParseException was expected");
        }
        catch (ParseException parseException){
            assertThat("Wrong Command").isEqualTo(parseException.getMessage());
        }
    }
}
