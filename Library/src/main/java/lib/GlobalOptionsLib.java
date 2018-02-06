package lib;

import java.util.*;

public class GlobalOptionsLib {

    String[] globalOptions = {"--file=", "--file2=", "--help"};

    public void help(boolean s){
        if (s){
            CommandList commandList = new CommandList();
            System.out.println(commandList.add1);
            System.out.println(commandList.search1);
            System.out.println(commandList.update1);
            System.out.println(commandList.list1);
            System.out.println(commandList.delete1);
        }
    }
}
