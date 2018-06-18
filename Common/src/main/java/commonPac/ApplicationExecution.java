package commonPac;

import commonPac.parser.Parser;
import commonPac.views.ErrorView;

public class ApplicationExecution {

    public void run(String[] args, ApplicationDescriptor applicationDescriptor){
        ApplicationDescriptor descriptor = applicationDescriptor;
        Parser parser = new Parser();

        InputParameters inputParameters = parser.parse(args, descriptor.getGlobalOptionsList(), descriptor.getCommandsList());

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
