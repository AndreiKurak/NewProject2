package lib;

import commonPac.OptionDescription;
import commonPac.OptionValidator;

public class OptionDescriptionBuilder {

    private String name;
    private boolean mandatory;
    private OptionValidator validator;

    OptionDescriptionBuilder(String name, OptionValidator validator){
        this.name = name;
        this.validator = validator;
    }

    public OptionDescriptionBuilder setNewName(String name){
        this.name = name;
        return this;
    }

    public OptionDescriptionBuilder setNewMandatory(boolean mandatory){
        this.mandatory = mandatory;
        return this;
    }

    public OptionDescriptionBuilder setNewValidator(OptionValidator validator){
        this.validator = validator;
        return this;
    }

    public OptionDescription createOption(){
        return new OptionDescription(name, validator, mandatory);
    }
}
