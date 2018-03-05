package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.util.*;

public class AddCommand implements Command {

    public void method1(InputParameters inputParameters, List<Map<String, String>> list){
        list.add(inputParameters.commandOptions);
        System.out.println("add was performed");
    }
}
