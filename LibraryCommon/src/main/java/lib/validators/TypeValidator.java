package lib.validators;

import common.validators.OptionValidator;

public class TypeValidator implements OptionValidator {

    private Object type;

    public TypeValidator(Object type){
        this.type = type;
    }

    public String check(String optionValue){
        String checkResult = "type of value is incorrect";

        System.out.println("check of the value type");
        try{
            Integer s2 = Integer.valueOf(optionValue);
            if (Integer.class.isInstance(s2) && Integer.class.isInstance(type)) {
                checkResult = null;
            }
        }
        catch (NumberFormatException ex){
            if (String.class.isInstance(optionValue) && String.class.isInstance(type)) {
                checkResult = null;
            }
        }

        return checkResult;
    }
}
