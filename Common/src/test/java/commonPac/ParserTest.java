package commonPac;

import org.junit.Test;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    Parser parser = new Parser();

    private static final List<OptionDescription> addOptions = new ArrayList<OptionDescription>(){{
        add(new OptionDescription("author", true));
        add(new OptionDescription("title", true));
        add(new OptionDescription("year", false));
    }};
    private static final List<CommandDescription> addList = new ArrayList<CommandDescription>(){{
        add(new CommandDescription("add", "add - command, that is used for adding new books to the library", addOptions));
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
        Assertions.assertThat("new author").isEqualTo(inputParameters.commandOptions.get("author"));
        Assertions.assertThat("2019").isEqualTo(inputParameters.commandOptions.get("year"));
    }

    @Test
    public void parseCorrectGlobalOptions(){
        String[] line ={"--file1=test", "--file2=test2", "add", "--author=new"};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);
        Assertions.assertThat("test").isEqualTo(inputParameters.globalOptions.get("file1"));
        Assertions.assertThat("test2").isEqualTo(inputParameters.globalOptions.get("file2"));
    }

    @Test
    public void parseCorrectCommand(){
        String[] line ={"--file1=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);
        Assertions.assertThat("add").isEqualTo(inputParameters.command);
    }

    @Test
    public void nullInputLine(){        //not works
        String[] line ={""};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);

    }

    @Test
    public void InputLineWithWrongCommand(){        //not works
        String[] line = {"--file1=test", "edd", "--author=new", "author", "--title=World", "--year=2019"};

        InputParameters inputParameters = parser.parse(line, globalOptions, addList);

    }
}
