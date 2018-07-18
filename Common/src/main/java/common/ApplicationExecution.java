package common;

import common.descriptions.CommandDescription;
import common.parser.Parser;
import common.views.ErrorView;

public class ApplicationExecution {

    public void run(String[] args, ApplicationDescriptor descriptor){
        Parser parser = new Parser();

        CommandDescription command = parser.parse(args, descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList());

        ViewModel viewModel = command.getCommandToExecute().setAndExecute(command.getCommandOptions(), null);

        if (viewModel.view != null){
            viewModel.view.showResult(viewModel.model);
        }
        else {
            viewModel.view = new ErrorView();
            viewModel.view.showResult("view не задано");
        }
    }
}
