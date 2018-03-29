package lib;

import commonPac.OptionValidator;

public class TitleValidator implements OptionValidator {

    private String name = "title";

    public String getName(){
        return name;
    }

    public boolean check(String s){
        boolean checkResult = false;

        try{
            Integer.valueOf(s);
        }
        catch (NumberFormatException ex){
            checkResult = true;
        }

        return checkResult;
    }
}
