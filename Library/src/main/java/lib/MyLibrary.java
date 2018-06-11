package lib;

import commonPac.*;
import commonPac.views.ErrorView;


public class MyLibrary {

    public static void main(String[] args){
        CommandEnumeration commandEnumeration = new CommandEnumeration();
        Parser parser = new Parser();

        InputParameters inputParameters = parser.parse(args, commandEnumeration.globalOptions, commandEnumeration.commands);

        Command command = inputParameters.getCommand().getCommandToExecute();
        ViewModel viewModel = command.execute(inputParameters);
        if (viewModel.view != null){
            viewModel.view.showResult(viewModel.model);
        }
        else {
            viewModel.view = new ErrorView();
            viewModel.view.showResult("view не задано");
        }
    }
}
