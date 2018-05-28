package lib;

import commonPac.*;


public class MyLibrary {

    public static void main(String[] args){
        CommandEnumeration commandEnumeration = new CommandEnumeration();
        Parser parser = new Parser();
        ViewController viewController = new ViewController(new SystemOutView());

        InputParameters inputParameters = parser.parse(args, commandEnumeration.globalOptions, commandEnumeration.commands);

        Command command1 = new  GlobalOptionsCommand();
        command1.execute(inputParameters, viewController);
        viewController.view.showResult(viewController.model);

        Command command = inputParameters.command.getCommand();
        command.execute(inputParameters, viewController);
        viewController.view.showResult(viewController.model);
    }
}
