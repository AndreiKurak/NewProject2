package commonPac;

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

    public OptionDescriptionBuilder addValidator(OptionValidator validator){
        this.validator = validator;
        return this;
    }

    public OptionDescription createOption(){
        return new OptionDescription(name, validator, mandatory);
    }
}
