package lib;

import java.util.*;

public class GlobalOptionsLib {

    String[] globalOptions = {"--file=", "--file2=", "--help"};

    public void help(boolean s){
        if (s){
            CommandList commandList = new CommandList();
            Iterator<List<String>> values = commandList.newOptions.values().iterator();
            Iterator<String> keys = commandList.newOptions.keySet().iterator();
            while (values.hasNext()){
                System.out.print(keys.next() + " ");
                System.out.println(values.next());
            }
        }
    }
}
