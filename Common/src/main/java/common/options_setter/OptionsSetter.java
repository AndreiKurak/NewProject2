package common.options_setter;

import java.lang.reflect.Field;

public class OptionsSetter {

    public void setOptions(String optionName, String option, Object options){
        try {
            Field field = options.getClass().getDeclaredField(optionName);
            field.setAccessible(true);
            field.set(options, option);
        }
        catch (Exception ex) {
            throw new OptionsSetterException("Error while setting options");
        }
    }
}
