package commonPac;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    Parser parser = new Parser();

    private static final List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", true));
        add(new OptionDescription("title", true));
        add(new OptionDescription("year", false));
    }};
    static class TestAdd implements Command{
        public void execute(InputParameters inputParameters, ViewController controller){}
    }
    private static final List<CommandDescription> addList = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "add - command, that is used for adding new books to the library", addOptions, new TestAdd()));
    }};
    private static final List<OptionDescription> globalOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("file1", true));
        add(new OptionDescription("file2", false));
        add(new OptionDescription("help", false));
    }};

    @Test
    public void parseCorrectOptions(){
        String[] line ={"--file1=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);
        assertThat("new author").isEqualTo(inputParameters.commandOptions.get("author"));
        assertThat("2019").isEqualTo(inputParameters.commandOptions.get("year"));
    }

    @Test
    public void parseCorrectGlobalOptions(){
        String[] line ={"--file1=test", "--file2=test2", "add", "--author=new"};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);
        assertThat("test").isEqualTo(inputParameters.globalOptions.get("file1"));
        assertThat("test2").isEqualTo(inputParameters.globalOptions.get("file2"));
    }

    @Test
    public void parseCorrectCommand(){
        String[] line ={"--file1=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);
        assertThat("add").isEqualTo(inputParameters.command.getName());
    }

    @Test//(expected = ParseException.class)
    public void nullInputLine(){    //should be another message
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
