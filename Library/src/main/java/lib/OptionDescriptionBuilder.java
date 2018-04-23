package lib;

import commonPac.OptionDescription;
import commonPac.OptionValidator;

public class OptionDescriptionBuilder {

    private String name;
    private boolean mandatory = false;
    private OptionValidator validator;

    OptionDescriptionBuilder(String name){
        this.name = name;
    }

    public OptionDescriptionBuilder setMandatoryTrue(){
        this.mandatory = true;
        return this;
    }

    public OptionDescriptionBuilder setValidator(OptionValidator validator){
        this.validator = validator;
        return this;
    }

    public OptionDescription createOption(){
        return new OptionDescription(name, validator, mandatory);
    }
}
