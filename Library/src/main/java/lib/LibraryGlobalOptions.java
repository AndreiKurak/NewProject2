package lib;

public class LibraryGlobalOptions {

    public void help(boolean s){
        if (s){
            CommandEnumeration commandEnumeration = new CommandEnumeration();
            for (int i = 0; i< commandEnumeration.commands.size(); i++){
                System.out.println(commandEnumeration.commands.get(i));
            }
            System.out.println("\n" + commandEnumeration.globalOptions);
        }
    }
}
