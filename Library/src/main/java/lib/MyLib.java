package lib;

import commonPac.Parser;
import commonPac.InputParameters;

public class MyLib {

    public static void main(String[] args){
        CommandList commandList = new CommandList();
        GlobalOptionsLib global = new GlobalOptionsLib();
        Parser parser = new Parser();

        InputParameters inputParameters = parser.parse(args, commandList.globalOptions, commandList.commands);
        System.out.println(inputParameters.command);
        System.out.println(inputParameters.globalOptions);
        System.out.println(inputParameters.commandOptions);
        global.help(inputParameters.globalOptions.containsKey("help"));
    }
}
