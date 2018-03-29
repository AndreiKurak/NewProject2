package lib;

import commonPac.OptionValidator;
import java.util.Date;

public class YearValidator implements OptionValidator{

    private String name = "year";

    public String getName(){
        return name;
    }

    public boolean check(String s){
        boolean checkResult = true;

        try{
            Integer.valueOf(s);
        }
        catch (NumberFormatException ex){
            checkResult = false;
        }
        if (!checkResult)
            return checkResult;

        Date date = new Date();
        if (1900 + date.getYear() < Integer.valueOf(s))   //??
            checkResult = false;
        return checkResult;
    }
}
