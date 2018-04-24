package commonPac;

import java.util.List;

public class ValidatorsChain {

    private List<OptionValidator> validatorList;

    public ValidatorsChain(List<OptionValidator> validators){
        this.validatorList = validators;
    }

    public boolean checkList(String optionValue){
        for (OptionValidator option : validatorList){
            if (!option.check(optionValue))
                return false;
        }
        return true;
    }
}
