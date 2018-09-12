package console_app;

import common.ApplicationExecution;
import common.views.ErrorView;
import common.views.ListView;
import common.views.MessageView;
import common.views.ViewResolver;
import framework_console.ConsoleOutput;
import lib.LibraryDescriptor;

public class ConsoleAppRunner {

    public static void main(String[] args){
        /*
        ViewResolver viewResolver = new ConsoleViewResolver(new ConsoleOutput());
        viewResolver.addView("MessageView", new MessageView(new ConsoleOutput()));
        viewResolver.addView("ListView", new ListView(new ConsoleOutput()));
        viewResolver.addView("ErrorView", new ErrorView(new ConsoleOutput()));
          */
        ApplicationExecution applicationExecution = new ApplicationExecution();
        applicationExecution.run(args, new LibraryDescriptor(), new ConsoleViewResolver(new ConsoleOutput()));
    }
}
