package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.util.*;

public class DeleteCommand implements Command {

    public void method1(InputParameters inputParameters, List<Map<String, String>> list){
        int id = Integer.valueOf(inputParameters.commandOptions.get("id")) - 1;
        list.remove(id);
        System.out.println("delete was performed");
    }
}
