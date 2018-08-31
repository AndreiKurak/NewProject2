package framework_web;

import common.ApplicationDescriptor;
import common.CommandWithOptions;
import common.ViewModel;
import common.parser.Parser;

import java.util.Map;

public class ApplicationExecution {

    public ViewModel run(Map<String, String[]> parameters, ApplicationDescriptor descriptor) {
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(parameters.get("inputQuery"), descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList(), descriptor.getGlobalOptions());

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());

        return viewModel;
    }
}
