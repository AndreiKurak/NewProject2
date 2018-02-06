package commonPac;

import java.util.*;

public class Parse {

    public static final String PREFIX = "--";
    public static final String Equality = "=";
    public static String error;

    public static InputParameters parse(String[] inputString, String[] globalOptions, Map<String, List<String>> map){
        InputParameters inputParameters = new InputParameters();
        String input = "";

        for (int i = 0; i<inputString.length; i++){
            input += inputString[i] + " ";
        }

        if (input.equals("")){
            error = "Empty input line";
            System.out.println(error);
            throw new NullPointerException();
        }

        boolean findResult = false;
        Set<String> newCommands = map.keySet();
        for (String i:newCommands){
            if (input.contains(i)){
                findResult = true;
                inputParameters.command = i;
                input = input.replaceAll(i, "");
                break;
            }
        }

        if (!findResult){
            error = "Wrong command";
            System.out.println(error);
            throw new NullPointerException();
        }

        String[] input2 = input.split((" " + PREFIX));
        for (int i = 1; i<input2.length; i++)
            input2[i] = PREFIX + input2[i].trim();

        for (int i = 0; i<input2.length; i++)
            for (int j = 0; j<globalOptions.length; j++)
                if (input2[i].contains(globalOptions[j])){
                    inputParameters.globalOptions.put(globalOptions[j], input2[i].replaceAll(globalOptions[j], ""));
                }
        System.out.println(Arrays.toString(input2));

        List<String> newOptions = map.get(inputParameters.command);
        for (int i = 0; i<input2.length; i++){
            for (int j = 0; j<newOptions.size(); j++)
                if (input2[i].contains(newOptions.get(j))){
                    inputParameters.commandOptions.put(newOptions.get(j), input2[i].replaceAll(PREFIX + newOptions.get(j) + Equality, ""));
                }
        }
        return inputParameters;
    }
}
