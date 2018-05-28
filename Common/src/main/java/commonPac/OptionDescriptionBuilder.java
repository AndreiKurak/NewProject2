package commonPac;

import java.util.Arrays;

public class OptionDescriptionBuilder {

    private String name;
    private boolean mandatory = false;
    private OptionValidator validator;

    public OptionDescriptionBuilder(String name){
        this.name = name;
    }

    public OptionDescriptionBuilder setMandatoryTrue(){
        this.mandatory = true;
        return this;
    }

   public OptionDescriptionBuilder addValidator(OptionValidator... validators){
       this.validator = new ValidatorsChain(Arrays.asList(validators));
       return this;
   }

    public OptionDescription createOption(){
        return new OptionDescription(name, validator, mandatory);
    }
}
