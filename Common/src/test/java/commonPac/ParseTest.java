package commonPac;

//import org.junit.Test;
//import org.junit.Assert;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseTest {

    static final String PREFIX = "--";
    static final String[] globalOptions = {PREFIX + "file=", PREFIX + "file2="};
    static final List<String> options = new ArrayList<String>(){{
        add("author");
        add("title");
        add("year");
           // {PREFIX + "author=", PREFIX + "title=", PREFIX + "year="}
    }};

    @Test                              //библиотека из maven
    public void parseCorrectOptions(){
        String[] line ={"--file=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
            put("add", options);
        }};
        InputParameters inputParameters = Parse.parse(line, globalOptions, newOptions);
        String commandTest1 = "new author";
        Assert.assertEquals(commandTest1, inputParameters.commandOptions.get("author"));
        String commandTest2 = "2019";
        Assert.assertEquals(commandTest2, inputParameters.commandOptions.get("year"));
    }

    @Test
    public void parseCorrectGlobalOptions(){
        String[] line ={"--file=test", "--file2=test2", "add", "--author=new"};

        Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
            put("add", options);
        }};
        InputParameters inputParameters = Parse.parse(line, globalOptions, newOptions);
        String commandTest1 = "test";
        Assert.assertEquals(commandTest1, inputParameters.globalOptions.get("--file="));
        String commandTest2 = "test2";
        Assert.assertEquals(commandTest2, inputParameters.globalOptions.get("--file2="));
    }

    @Test
    public void parseCorrectCommand(){
        String[] line ={"--file=test", "add", "--author=new", "author", "--title=World", "--year=2019"};

        Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
            put("add", options);
        }};
        InputParameters inputParameters = Parse.parse(line, globalOptions, newOptions);
        String commandTest = "add";
        Assert.assertEquals(commandTest, inputParameters.command);
    }

    @Test
    public void nullInputLine(){
        String[] line ={""};

        Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
            put("add", options);
        }};
        InputParameters inputParameters = Parse.parse(line, globalOptions, newOptions);
        Assert.assertEquals("Empty input line", Parse.error);
    }

    @Test
    public void InputLineWithWrongCommand(){
        String[] line = {"--file=test", "edd", "--author=new", "author", "--title=World", "--year=2019"};

        Map<String, List<String>> newOptions = new HashMap<String, List<String>>(){{
            put("add", options);
        }};
        InputParameters inputParameters = Parse.parse(line, globalOptions, newOptions);
        Assert.assertEquals("Wrong command", Parse.error);
    }

}
