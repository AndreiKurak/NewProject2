package lib.validators;

import common.validators.OptionValidator;

import java.util.Date;

public class DateBorderValidator implements OptionValidator {

    public boolean check(String optionValue){
        boolean checkResult = true;
        System.out.println("check of the data value");

        Date date = new Date();
        if (1900 + date.getYear() < Integer.valueOf(optionValue))
            checkResult = false;

        return checkResult;
    }
}
