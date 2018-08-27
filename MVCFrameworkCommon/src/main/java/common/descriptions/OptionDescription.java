package common.descriptions;

import common.validators.OptionValidator;
import common.validators.ValidatorsChain;

import java.util.Arrays;

public class OptionDescription {

    private String name;
    private boolean isMandatory = false;
    private OptionValidator validator;

    public OptionDescription(String name) {
        this.name = name;
    }

    public OptionDescription(String name, OptionValidator validator, boolean isMandatory) {
        this.name = name;
        this.validator = validator;
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

    public OptionValidator getValidator() {
        return validator;
    }

    public void setValidator(OptionValidator validator) {
        this.validator = validator;
    }

    public OptionDescription addValidator(OptionValidator validator) {
        this.validator = validator;
        return this;
    }

    public OptionDescription addValidator(OptionValidator... validators) {
        this.validator = new ValidatorsChain(Arrays.asList(validators));
        return this;
    }

    public String toString() {
        return name + ": isMandatory  =  " + isMandatory;
    }
}
