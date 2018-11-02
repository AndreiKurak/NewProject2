package common.descriptions;

import common.validators.OptionValidator;
import common.validators.ValidatorsChain;

import java.util.Arrays;

public class OptionDescription {

    private String name;
    private boolean isMandatory = false;
    private OptionValidator validator2;

    public OptionDescription(String name) {
        this.name = name;
    }

    public OptionDescription(String name, OptionValidator validator2, boolean isMandatory) {
        this.name = name;
        this.validator2 = validator2;
        this.isMandatory = isMandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public OptionDescription setMandatoryTrue() {
        this.isMandatory = true;
        return this;
    }

    public OptionValidator getValidator2() {
        return validator2;
    }

    public OptionDescription addValidator(OptionValidator validator) {
        this.validator2 = validator;
        return this;
    }

    public OptionDescription addValidator(OptionValidator... validators) {
        this.validator2 = new ValidatorsChain(Arrays.asList(validators));
        return this;
    }

    public String toString() {
        return name + ": isMandatory  =  " + isMandatory;
    }
}
