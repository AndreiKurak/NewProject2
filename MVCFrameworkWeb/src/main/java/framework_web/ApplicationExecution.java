package framework_web;

import common.ApplicationDescriptor;
import common.CommandWithOptions;
import common.ViewModel;
import common.parser.Parser;
import common.views.ViewResolver;

import java.io.PrintWriter;
import java.util.Map;

public class ApplicationExecution {

    public ViewModel run(Map<String, String[]> parameters, ApplicationDescriptor descriptor, PrintWriter writer){
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(parameters.get("inputQuery"), descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList(), descriptor.getGlobalOptions());

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());
    /////
        ViewResolver resolver = new ViewResolver(new WebPageOutput(writer));
        if (viewModel.getViewName() != null) {
            resolver.getView(viewModel.getViewName()).showResult(viewModel.getModel());
        }
        else {
            resolver.getView("ErrorView").showResult("View is not specified");
        }
        return viewModel;
    }
}
