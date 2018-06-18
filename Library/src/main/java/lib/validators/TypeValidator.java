package lib.validators;

import commonPac.validators.OptionValidator;

public class TypeValidator implements OptionValidator{

    private Object type;

    public TypeValidator(Object type){
        this.type = type;
    }

    public boolean check(String optionValue){
        boolean checkResult = false;

        System.out.println("проверка типа");
        try{
            int s2 = Integer.valueOf(optionValue);
            if (Integer.class.isInstance(s2) && Integer.class.isInstance(type)) {
                checkResult = true;
            }
        }
        catch (NumberFormatException ex){
            if (String.class.isInstance(optionValue) && String.class.isInstance(type)) {
                checkResult = true;
            }
        }

        return checkResult;
    }
}
