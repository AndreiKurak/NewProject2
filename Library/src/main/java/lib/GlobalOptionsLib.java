package lib;

public class GlobalOptionsLib {

    public void help(boolean s){
        if (s){
            OptionLists optionLists = new OptionLists();
            CommandList commandList = new CommandList();
            for (int i = 0; i<commandList.commands.size(); i++){
                System.out.println(commandList.commands.get(i));
            }
            System.out.println("\n" + optionLists.globalOptions);
        }
    }
}
