package lib;

import commonPac.OptionValidator;

public class AuthorValidator implements OptionValidator {

    private String name = "author";

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
