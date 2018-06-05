package lib;

import commonPac.*;

public class GlobalOptionsCommand implements Command {

    private static final String FILE = "file1";

    public ViewModel execute(InputParameters inputParameters){
        if (inputParameters.globalOptions.containsKey(FILE)){

        }
        return null;
    }
}
