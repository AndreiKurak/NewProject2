package lib;

import common.*;

public class MyLibrary {

    public static void main(String[] args){
        ApplicationExecution applicationExecution = new ApplicationExecution();
        applicationExecution.run(args, new LibraryDescriptor());
    }
}
