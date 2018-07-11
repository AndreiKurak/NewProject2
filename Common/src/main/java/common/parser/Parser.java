package common.parser;

import common.ExecutableCommand;
import common.OptionsSetter;
import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;

import java.util.*;

public class Parser {

    public static final String PREFIX = "--";
    public static final String Equality = "=";

    public ExecutableCommand parse(String[] inputString, List<OptionDescription> globalOptions, List<CommandDescription> commands){
        ExecutableCommand command = null;
        OptionsSetter optionsSetter = new OptionsSetter();
        String input = "";

        for (int i = 0; i<inputString.length; i++){
            input += inputString[i] + " ";
        }

        if (input.equals(" ")){
            throw new ParseException("Empty input line");
        }

        boolean findResult = false;

        int requred = 0; //
        for (int i = 0; i<commands.size(); i++){
            if (input.contains(commands.get(i).getName())){
                findResult = true;
                command = commands.get(i).getCommandToExecute();
                input = input.replaceAll(commands.get(i).getName(), "");
                requred = i;
                break;
            }
        }
        List<OptionDescription> newOptions = commands.get(requred).getOptions();

        if (!findResult){
            throw new ParseException("Wrong Command");
        }

        String[] input2 = input.split((" " + PREFIX));
        for (int i = 1; i<input2.length; i++)
            input2[i] = PREFIX + input2[i].trim();

        for (int i = 0; i<input2.length; i++)
            for (int j = 0; j<globalOptions.size(); j++)
                if (input2[i].contains(globalOptions.get(j).getName())){
                    optionsSetter.setOptions(globalOptions.get(j).getName(), input2[i].replaceAll(PREFIX + globalOptions.get(j).getName() + Equality, ""), null); //command.getGlobalOptions());
                }
        System.out.println(Arrays.toString(input2));

        boolean gotIt = false;
        for (int i = 0; i<newOptions.size(); i++){
            for (int j = 0; j<input2.length; j++)
                if (input2[j].contains(newOptions.get(i).getName())){
                    optionsSetter.setOptions(newOptions.get(i).getName(), input2[j].replaceAll(PREFIX + newOptions.get(i).getName() + Equality, ""), null);// command.getOptions());
                    if (newOptions.get(i).getValidator() != null)
                        if (!newOptions.get(i).getValidator().check(input2[j].replaceAll(PREFIX + newOptions.get(i).getName() + Equality, "")))
                            throw new ParseException("Unacceptable option value");
                    gotIt = true;
                }
            if (!gotIt && newOptions.get(i).getMandatory()){
                throw new ParseException("Required option missed");
            }
        }

        return command;
    }
}