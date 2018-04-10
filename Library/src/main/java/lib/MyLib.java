package lib;

import commonPac.Command;
import commonPac.Parser;
import commonPac.InputParameters;


public class MyLib {

    public static void main(String[] args){
        CommandEnumeration commandEnumeration = new CommandEnumeration();
        LibraryGlobalOptions global = new LibraryGlobalOptions();
        Parser parser = new Parser();

        InputParameters inputParameters = parser.parse(args, commandEnumeration.globalOptions, commandEnumeration.commands);

        //System.out.println(inputParameters.command.getName() + " " + inputParameters.globalOptions + " " + inputParameters.commandOptions);
        Command command = inputParameters.command.getCommand();
        global.help(inputParameters.globalOptions.containsKey("help"));

        command.execute(inputParameters);
    }
}
