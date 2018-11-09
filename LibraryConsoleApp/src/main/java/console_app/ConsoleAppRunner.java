package console_app;

import common.ApplicationExecution;
import common.views.*;
import framework_console.ConsoleOutput;
import framework_console.ConsoleParametersParser;
import lib.PropertyValuesGetter;
import lib.connectors.SessionFactoryGetter;
import lib.library_entities.Book;
import org.hibernate.cfg.Configuration;

public class ConsoleAppRunner {

    public static void main(String[] args){
        OutputWindowViewResolver viewResolver = new OutputWindowViewResolver(new ConsoleOutput());
        viewResolver.addView("MessageView", MessageView.class);
        viewResolver.addView("ListView", ListView.class);
        viewResolver.addView("ErrorView", ErrorView.class);
        //
        SessionFactoryGetter.setSessionFactory(new Configuration().
                addPackage("lib.library_entities").
                addProperties(new PropertyValuesGetter().getProp()).
                addAnnotatedClass(Book.class).buildSessionFactory());
        //
        ApplicationExecution applicationExecution = new ApplicationExecution();
        applicationExecution.run(new ConsoleParametersParser(args), new ConsoleLibraryDescriptor(), viewResolver);
        //
        SessionFactoryGetter.getSessionFactory().close();
        //
    }
}
