package lib;

import commonPac.Command;
import commonPac.Parser;
import commonPac.InputParameters;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLib {

    private static final String DIRECTORY = "D:\\";
    private static final String FILENAME = "D:\\library.ser";

    public static void main(String[] args){
        CommandEnumeration commandEnumeration = new CommandEnumeration();
        LibraryGlobalOptions global = new LibraryGlobalOptions();
        Parser parser = new Parser();

        InputParameters inputParameters = parser.parse(args, commandEnumeration.globalOptions, commandEnumeration.commands);
     /*   System.out.println(inputParameters.command.getName());
        System.out.println(inputParameters.globalOptions);
        System.out.println(inputParameters.commandOptions); */
        Command command = inputParameters.command.getCommand();
        global.help(inputParameters.globalOptions.containsKey("help"));

        try {
            File f = new File(FILENAME);
            long len = f.length();
            if (len == 0){
                BooksRegister myObject = new BooksRegister();
                myObject.realizeCommand(command, inputParameters);
                final FileOutputStream fos = new FileOutputStream(DIRECTORY + "library.ser");
                final ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(myObject);
            }
            if (len != 0){
                FileInputStream fin = new FileInputStream(FILENAME);
                ObjectInputStream ois = new ObjectInputStream(fin);
                BooksRegister newObject = (BooksRegister) ois.readObject();
                final FileOutputStream fos = new FileOutputStream(DIRECTORY + "library.ser");
                final ObjectOutputStream oos = new ObjectOutputStream(fos);
                newObject.realizeCommand(command, inputParameters);
                oos.writeObject(newObject);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyLib.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyLib.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex){
            Logger.getLogger(MyLib.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
