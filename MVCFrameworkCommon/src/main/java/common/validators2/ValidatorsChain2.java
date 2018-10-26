package common.validators2;

import java.util.List;

public class ValidatorsChain2 implements OptionValidator2 {

    private List<OptionValidator2> validatorList;

    public ValidatorsChain2(List<OptionValidator2> validators){
        this.validatorList = validators;
    }

    public String check(String optionValue){
        String checkResult = null;
        for (OptionValidator2 option : validatorList){
            checkResult = option.check(optionValue);
            if (checkResult != null)
                return checkResult;
        }
        return checkResult;
    }
}
