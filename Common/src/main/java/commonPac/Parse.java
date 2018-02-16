package commonPac;

import java.util.*;

public class Parse {

    public static final String PREFIX = "--";
    public static final String Equality = "=";
    public static String error = "";

    public InputParameters parse(String[] inputString, List<OptionDescription> globalOptions, List<CommandDescription> commands) {
        InputParameters inputParameters = new InputParameters();
        String input = "";

        for (int i = 0; i<inputString.length; i++){
            input += inputString[i] + " ";
        }

        if (input.equals("")){
            error = "Empty input line";
            throw new NullPointerException(error);
            //return null;
        }

        boolean findResult = false;

        int requred = 0; //
        for (int i = 0; i<commands.size(); i++){
            if (input.contains(commands.get(i).getName())){
                findResult = true;
                inputParameters.command = commands.get(i).getName();
                input = input.replaceAll(commands.get(i).getName(), "");
                requred = i;
                break;
            }
        }
        List<OptionDescription> newOptions = commands.get(requred).getOptions();

        if (!findResult){
            error = "Wrong command";
            throw new NullPointerException(error);
            //return null;
        }

        String[] input2 = input.split((" " + PREFIX));
        for (int i = 1; i<input2.length; i++)
            input2[i] = PREFIX + input2[i].trim();

        for (int i = 0; i<input2.length; i++)
            for (int j = 0; j<globalOptions.size(); j++)
                if (input2[i].contains(globalOptions.get(j).getName())){
                    inputParameters.globalOptions.put(globalOptions.get(j).getName(), input2[i].replaceAll(PREFIX + globalOptions.get(j).getName() + Equality, ""));
                }
        System.out.println(Arrays.toString(input2));

        for (int i = 0; i<input2.length; i++)
            for (int j = 0; j<newOptions.size(); j++)
                if (input2[i].contains(newOptions.get(j).getName()))
                    inputParameters.commandOptions.put(newOptions.get(j).getName(), input2[i].replaceAll(PREFIX + newOptions.get(j).getName() + Equality, ""));
        return inputParameters;
    }
}
