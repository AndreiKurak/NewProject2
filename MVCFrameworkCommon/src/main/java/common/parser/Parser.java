package common.parser;

import common.CommandWithOptions;
import common.options_setter.OptionsSetter;
import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;

import java.util.*;

public class Parser {

    public static final String PREFIX = "--";
    public static final String Equality = "=";

    public CommandWithOptions parse(String[] inputString, List<OptionDescription> globalOptionsDescription, List<CommandDescription> commands, Object globalOptions){
        OptionsSetter optionsSetter = new OptionsSetter();
        CommandDescription commandDescription = null;
        CommandWithOptions command = new CommandWithOptions();
        String input = "";

        for (int i = 0; i<inputString.length; i++){
            input += inputString[i] + " ";
        }

        if (input.equals(" ")){
            throw new ParseException("Empty input line");
        }

        boolean findResult = false;

        int required = 0;
        for (int i = 0; i<commands.size(); i++){
            if (input.contains(commands.get(i).getName())){
                findResult = true;
                commandDescription = commands.get(i);
                command.setCommand(commandDescription.createAndGetCommand());
                input = input.replaceAll(commands.get(i).getName() + " ", "");
                required = i;
                break;
            }
        }

        if (!findResult){
            throw new ParseException("Wrong Command");
        }
        List<OptionDescription> newOptions = commands.get(required).getOptions();
        String[] input2 = input.split((" " + PREFIX));

        command.setGlobalOptions(globalOptions);
        for (int i = 1; i<input2.length; i++)
            input2[i] = PREFIX + input2[i].trim();

        for (int i = 0; i<input2.length; i++)
            for (int j = 0; j<globalOptionsDescription.size(); j++)
                if (input2[i].contains(globalOptionsDescription.get(j).getName())){
                    optionsSetter.setOptions(globalOptionsDescription.get(j).getName(), input2[i].replaceAll(PREFIX + globalOptionsDescription.get(j).getName() + Equality, ""), command.getGlobalOptions());
                }
        System.out.println(Arrays.toString(input2));

        command.setCommandOptions(commandDescription.createAndGetCommandOptions());
        boolean gotIt = false;
        for (int i = 0; i<newOptions.size(); i++){
            for (int j = 0; j<input2.length; j++)
                if (input2[j].contains(newOptions.get(i).getName())){
                    optionsSetter.setOptions(newOptions.get(i).getName(), input2[j].replaceAll(PREFIX + newOptions.get(i).getName() + Equality, ""), command.getCommandOptions());
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
