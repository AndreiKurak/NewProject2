package commonPac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidatorsChain implements OptionValidator {

    private List<OptionValidator> validatorList;

    public ValidatorsChain(List<OptionValidator> validators){
        this.validatorList = validators;
    }

    public boolean check(String optionValue){
        for (OptionValidator option : validatorList){
            if (!option.check(optionValue))
                return false;
        }
        return true;
    }
}
