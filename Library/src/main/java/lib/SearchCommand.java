package lib;

import commonPac.Command;
import commonPac.InputParameters;

import java.util.*;

public class SearchCommand implements Command {

    public void method1(InputParameters inputParameters, List<Map<String, String>> list){
        boolean contains = false;
        for (Map<String, String> bookParameters : list){
            for (String key : inputParameters.commandOptions.keySet()){
                if (bookParameters.containsKey(key) && bookParameters.get(key).equals(inputParameters.commandOptions.get(key)))
                    contains = true;
                else{
                    contains = false;
                    break;
                }
            }
            if (contains){
                System.out.println(bookParameters);
            }
        }
        System.out.println("search was performed");
    }
}
