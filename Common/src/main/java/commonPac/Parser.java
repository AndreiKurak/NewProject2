package commonPac;

import java.util.*;

public class Parser {

    public static final String PREFIX = "--";
    public static final String Equality = "=";

    public InputParameters parse(String[] inputString, List<OptionDescription> globalOptions, List<CommandDescription> commands) {
        InputParameters inputParameters = new InputParameters();
        String input = "";

        for (int i = 0; i<inputString.length; i++){
            input += inputString[i] + " ";
        }

        try{
            if (input.equals("")){
                throw new ParseException();
            }
        }
        catch (ParseException ex){
            ex.emptyLine();
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

        try {
            if (!findResult && !input.equals("")){
                throw new ParseException();
            }
        }
        catch (ParseException ex){
            ex.noCommand();
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

        boolean gotIt = false;
        for (int i = 0; i<newOptions.size(); i++){
            for (int j = 0; j<input2.length; j++)
                if (input2[j].contains(newOptions.get(i).getName())){
                    inputParameters.commandOptions.put(newOptions.get(i).getName(), input2[j].replaceAll(PREFIX + newOptions.get(i).getName() + Equality, ""));
                    gotIt = true;
                }
            if (!gotIt && newOptions.get(i).getMandatory())
                System.out.println("Required option missed");
            gotIt = false;
        }
        return inputParameters;
    }
}
