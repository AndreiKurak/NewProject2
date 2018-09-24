package framework_web;

import common.ApplicationDescriptor;
import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;
import common.parser.ParametersParser;
import common.parser.ParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebParametersParser implements ParametersParser {
    
    private CommandDescription command;
    private Map<String, Object> commandOptions = new HashMap<>();
    private Map<String, Object> globalOptions = new HashMap<>();

    private static final String PREFIX = "--";
    private static final String Equality = "=";

    public WebParametersParser(Map inputMap, ApplicationDescriptor applicationDescriptor) {
        String[] input = (String[]) inputMap.values().iterator().next();
        //set fields
        String inputString = "";

        for (int i = 0; i<input.length; i++){
            inputString += input[i] + " ";
        }

        if (inputString.equals(" ")){
            throw new ParseException("Empty input line");
        }

        boolean findResult = false;

        int required = 0;
        List<CommandDescription> commands = applicationDescriptor.getCommandsDescriptionList();
        for (int i = 0; i<commands.size(); i++){
            if (inputString.contains(commands.get(i).getName())){
                findResult = true;
                command = commands.get(i);
                inputString = inputString.replaceAll(commands.get(i).getName() + " ", "");
                required = i;
                break;
            }
        }

        if (!findResult){
            throw new ParseException("Wrong Command");
        }
        List<OptionDescription> newOptions = commands.get(required).getOptions();
        String[] input2 = inputString.split((" " + PREFIX));

        for (int i = 1; i<input2.length; i++)
            input2[i] = PREFIX + input2[i].trim();

        List<OptionDescription> globalOptionsDescription = applicationDescriptor.getGlobalOptionsDescriptionList();
        for (int i = 0; i<input2.length; i++)
            for (int j = 0; j<globalOptionsDescription.size(); j++)
                if (input2[i].contains(globalOptionsDescription.get(j).getName())){
                    globalOptions.put(globalOptionsDescription.get(j).getName(), input2[i].replaceAll(PREFIX + globalOptionsDescription.get(j).getName() + Equality, ""));
                }
        System.out.println(Arrays.toString(input2));

        boolean gotIt = false;
        for (int i = 0; i<newOptions.size(); i++) {
            for (int j = 0; j < input2.length; j++)
                if (input2[j].contains(newOptions.get(i).getName())) {
                    commandOptions.put(newOptions.get(i).getName(), input2[j].replaceAll(PREFIX + newOptions.get(i).getName() + Equality, ""));
                    gotIt = true;
                }
            if (!gotIt && newOptions.get(i).getMandatory())
                throw new ParseException("Required option missed");
        }
    }

    @Override
    public CommandDescription getCommand() {
        return command;
    }

    @Override
    public Map<String, Object> getCommandOptions() {
        return commandOptions;
    }

    @Override
    public Map<String, Object> getGlobalOptions() {
        return globalOptions;
    }
}
