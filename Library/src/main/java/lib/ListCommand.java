package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.util.List;
import java.util.Map;

public class ListCommand implements Command {

    public void method1(InputParameters inputParameters, List<Map<String, String>> list){
        if (inputParameters.commandOptions.containsKey("all"))
            System.out.println(list);
        else{
            String key = null;
            for (String parameters : inputParameters.commandOptions.keySet())
                key = parameters.substring(0, parameters.length() - 1);
            for (Map<String, String> bookParameters : list)
                if (bookParameters.containsKey(key))
                    System.out.println(bookParameters.get(key));
        }
        System.out.println("list was performed");
    }
}
