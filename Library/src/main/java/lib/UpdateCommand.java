package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.util.*;

public class UpdateCommand implements Command {

    public void method1(InputParameters inputParameters, List<Map<String, String>> list){
        int id = Integer.valueOf(inputParameters.commandOptions.get("id")) - 1;
        for (String key : inputParameters.commandOptions.keySet()){
            if (list.get(id).containsKey(key))
                list.get(id).replace(key, inputParameters.commandOptions.get(key));
            else if (!key.equals("id"))
                list.get(id).put(key, inputParameters.commandOptions.get(key));
        }
        System.out.println("update was performed");
    }
}
