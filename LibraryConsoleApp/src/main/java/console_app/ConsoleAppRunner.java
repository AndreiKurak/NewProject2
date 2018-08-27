package console_app;

import framework_console.ApplicationExecution;
import lib.LibraryDescriptor;

public class ConsoleAppRunner {

    public static void main(String[] args){
        ApplicationExecution applicationExecution = new ApplicationExecution();
        applicationExecution.run(args, new LibraryDescriptor());
    }
}
