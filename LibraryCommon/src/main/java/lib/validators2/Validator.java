package lib.validators2;

import common.descriptions.CommandDescription;
import common.descriptions.OptionDescription;
import lib.LibraryDescriptor;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validator {

    public String validate(Object commandOptions, String commandName) {
        String result = null;
        try {
            CommandDescription commandDescription = new LibraryDescriptor().getCommandsDescriptionMap().get(commandName);

            Field[] fields = commandOptions.getClass().getDeclaredFields();

            for (OptionDescription option : commandDescription.getOptions()) {
                if (result != null) {
                    break;
                }
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (option.getName().equals(field.getName())) {
                        if ((String) field.get(commandOptions) != null)
                            result = option.getValidator2().check((String) field.get(commandOptions));
                        else if (option.getMandatory())
                            result = "mandatory option missed";
                    }
                }
                if (result != null)
                    result += " - " + option.getName();
            }
        }
        catch (Exception ex) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
        return result;
    }
}
