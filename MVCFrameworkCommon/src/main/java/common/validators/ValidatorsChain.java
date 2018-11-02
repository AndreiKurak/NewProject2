package common.validators;

import java.util.List;

public class ValidatorsChain implements OptionValidator {

    private List<OptionValidator> validatorList;

    public ValidatorsChain(List<OptionValidator> validators){
        this.validatorList = validators;
    }

    public String check(String optionValue){
        String checkResult = null;
        for (OptionValidator option : validatorList){
            checkResult = option.check(optionValue);
            if (checkResult != null)
                return checkResult;
        }
        return checkResult;
    }
}
