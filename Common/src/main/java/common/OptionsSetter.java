package common;

import java.lang.reflect.Field;

public class OptionsSetter {

    public void setOptions(String optionName, String option, Object options){
        try {
            Field field = options.getClass().getDeclaredField(optionName);
            field.setAccessible(true);
            field.set(options, option);
        }
        catch (Exception ex) {
            System.out.println(ex.getClass());
        }
    }
}
