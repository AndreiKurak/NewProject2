package commonPac;

import java.util.Arrays;
import java.util.List;

public class ValidatorsChain implements OptionValidator {

    private List<OptionValidator> validatorList;

    public ValidatorsChain(OptionValidator... validators){
        this.validatorList = Arrays.asList(validators);
    }

    public boolean check(String optionValue){
        for (OptionValidator option : validatorList){
            if (!option.check(optionValue))
                return false;
        }
        return true;
    }
}
