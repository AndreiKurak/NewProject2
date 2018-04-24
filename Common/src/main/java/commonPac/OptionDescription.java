package commonPac;

public class OptionDescription {

    private String name;
    private boolean isMandatory;
    private ValidatorsChain validator;

    public OptionDescription(String name, boolean isMandatory){
        this.name = name;
        this.isMandatory = isMandatory;
    }

    public OptionDescription(String name, ValidatorsChain validator, boolean isMandatory){
        this.name = name;
        this.validator = validator;
        this.isMandatory = isMandatory;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getMandatory(){
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory){
        this.isMandatory = isMandatory;
    }

    public ValidatorsChain getValidator() {
        return validator;
    }

    public void setValidator(ValidatorsChain validator) {
        this.validator = validator;
    }

    public String toString(){
        return name + ": isMandatory = " + isMandatory;
    }
}
