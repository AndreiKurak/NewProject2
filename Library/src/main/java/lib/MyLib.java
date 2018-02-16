package lib;

import commonPac.Parse;
import commonPac.InputParameters;

public class MyLib {

    public static void main(String[] args){
        OptionLists optionLists = new OptionLists();
        CommandList commandList = new CommandList();
        GlobalOptionsLib global = new GlobalOptionsLib();
        Parse parser = new Parse();

        InputParameters inputParameters = parser.parse(args, optionLists.globalOptions, commandList.commands);
        System.out.println(inputParameters.command);
        System.out.println(inputParameters.globalOptions);
        System.out.println(inputParameters.commandOptions);
        global.help(inputParameters.globalOptions.containsKey("help"));
    }
}
