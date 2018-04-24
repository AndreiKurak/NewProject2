package lib;

import commonPac.OptionDescription;
import commonPac.ValidatorsChain;

public class OptionDescriptionBuilder {

    private String name;
    private boolean mandatory = false;
    private ValidatorsChain validator;

    OptionDescriptionBuilder(String name){
        this.name = name;
    }

    public OptionDescriptionBuilder setMandatoryTrue(){
        this.mandatory = true;
        return this;
    }

    public OptionDescriptionBuilder setValidator(ValidatorsChain validator){
        this.validator = validator;
        return this;
    }

    public OptionDescription createOption(){
        return new OptionDescription(name, validator, mandatory);
    }
}
