package console_app;

import common.ApplicationExecution;
import common.ViewModel;
import common.views.*;
import framework_console.ConsoleOutput;
import framework_console.ConsoleParametersParser;
import lib.LibraryDescriptor;

public class ConsoleAppRunner {

    public static void main(String[] args){
        OutputWindowViewResolver viewResolver = new OutputWindowViewResolver(new ConsoleOutput());
        viewResolver.addView("MessageView", MessageView.class);
        viewResolver.addView("ListView", ListView.class);
        viewResolver.addView("ErrorView", ErrorView.class);

        ApplicationExecution applicationExecution = new ApplicationExecution();
        applicationExecution.run(new ConsoleParametersParser(args), new ConsoleLibraryDescriptor(), viewResolver);
    }
}
