package lib;

public class GlobalOptionsLib {

    public void help(boolean s){
        if (s){
            CommandList commandList = new CommandList();
            for (int i = 0; i<commandList.commands.size(); i++){
                System.out.println(commandList.commands.get(i));
            }
            System.out.println("\n" + commandList.globalOptions);
        }
    }
}
