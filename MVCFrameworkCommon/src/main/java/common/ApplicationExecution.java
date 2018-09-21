package common;

import common.parser.ParametersParser;
import common.parser.Parser;
import common.views.ViewResolver;

public class ApplicationExecution {

    public void run(ParametersParser parametersParser, ApplicationDescriptor descriptor, ViewResolver viewResolver){
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(parametersParser, descriptor);

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());

        if (viewModel.getViewName() != null) {
            viewResolver.getView(viewModel.getViewName()).showResult(viewModel.getModel());
        }
        else {
            viewResolver.getView("ErrorView").showResult("View is not specified");
        }
    }
}
