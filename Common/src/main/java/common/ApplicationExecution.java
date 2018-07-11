package common;

import common.parser.Parser;
import common.views.ErrorView;

public class ApplicationExecution {

    public void run(String[] args, ApplicationDescriptor descriptor){
        Parser parser = new Parser();

        ExecutableCommand command = parser.parse(args, descriptor.getGlobalOptionsList(), descriptor.getCommandsList());
      /*
        ViewModel viewModel = command.setAndExecute();

        if (viewModel.view != null){
            viewModel.view.showResult(viewModel.model);
        }
        else {
            viewModel.view = new ErrorView();
            viewModel.view.showResult("view не задано");
        }     */
    }
}
