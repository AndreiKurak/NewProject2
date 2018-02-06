package lib;

import commonPac.Parse;
import commonPac.InputParameters;

public class MyLib {

    public static void main(String[] args){
        CommandList commandList = new CommandList();
        GlobalOptionsLib global = new GlobalOptionsLib();

        Parse.parse(args, global.globalOptions, commandList.newOptions);
        if (Parse.error.equals("")){
            InputParameters inputParameters = Parse.parse(args, global.globalOptions, commandList.newOptions);
            System.out.println(inputParameters.command);
            System.out.println(inputParameters.globalOptions);
            System.out.println(inputParameters.commandOptions);
            global.help(inputParameters.globalOptions.containsKey("--help"));
        }
    }
}
