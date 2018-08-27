package framework_web;

import common.ApplicationDescriptor;
import common.CommandWithOptions;
import common.ViewModel;
import common.parser.Parser;
import common.views.ErrorView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationExecution {

    public void run(String[] args, ApplicationDescriptor descriptor, HttpServletRequest request, HttpServletResponse response){
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(args, descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList(), descriptor.getGlobalOptions());

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());

        if (viewModel.getView() != null){
            viewModel.getView().showResult(viewModel.getModel(), new WebPageOutput(request, response));
        }
        else {
            viewModel.setView(new ErrorView());
            viewModel.getView().showResult("view не задано", new WebPageOutput(request, response));
        }
    }
}
