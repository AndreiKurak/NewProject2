package common;

import common.parser.Parser;
import common.views.ErrorView;

public class ApplicationExecution {

    public void run(String[] args, ApplicationDescriptor descriptor){
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(args, descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList(), descriptor.getGlobalOptions());

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());

        if (viewModel.getView() != null){
            viewModel.getView().showResult(viewModel.getModel());
        }
        else {
            viewModel.setView(new ErrorView());
            viewModel.getView().showResult("view не задано");
        }
    }
}
