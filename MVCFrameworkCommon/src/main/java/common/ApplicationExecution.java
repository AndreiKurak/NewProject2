package common;

import common.parser.Parser;
import common.views.ViewResolver;

public class ApplicationExecution {

    public void run(String[] args, ApplicationDescriptor descriptor, ViewResolver viewResolver){
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(args, descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList(), descriptor.getGlobalOptions());

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());
        
        ViewResolver resolver = viewResolver;
        if (viewModel.getViewName() != null) {
            resolver.getView(viewModel.getViewName()).showResult(viewModel.getModel());
        }
        else {
            resolver.getView("ErrorView").showResult("View is not specified");
        }
    }
}
