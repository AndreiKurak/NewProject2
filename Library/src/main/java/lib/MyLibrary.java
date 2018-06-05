package lib;

import commonPac.*;


public class MyLibrary {

    public static void main(String[] args){
        CommandEnumeration commandEnumeration = new CommandEnumeration();
        Parser parser = new Parser();

        InputParameters inputParameters = parser.parse(args, commandEnumeration.globalOptions, commandEnumeration.commands);

        Command globalCommand = new GlobalOptionsCommand();
        globalCommand.execute(inputParameters);

        Command command = inputParameters.command.getCommand();
        command.execute(inputParameters).view.showResult(command.execute(inputParameters).model);
    }
}
