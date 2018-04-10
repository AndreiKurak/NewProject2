package lib;

import commonPac.OptionValidator;

import java.util.Date;

public class DateBorderValidator extends OptionValidator {

    public boolean check(String optionValue){
        boolean checkResult = true;
        System.out.println("проверка значения даты");

        Date date = new Date();
        if (1900 + date.getYear() < Integer.valueOf(optionValue))   //??
            checkResult = false;

        if (checkResult && isNextExist())
            return checkNext(optionValue);
        else
            return checkResult;
    }
}
