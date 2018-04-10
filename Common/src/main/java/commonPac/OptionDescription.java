package commonPac;

public class OptionDescription {

    private String name;
    private boolean isMandatory;
    private OptionValidator validator;

    public OptionDescription(String name, boolean isMandatory){
        this.name = name;
        this.isMandatory = isMandatory;
    }

    public OptionDescription(String name, OptionValidator validator){
        this.name = name;
        this.validator = validator;
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

    public OptionValidator getValidator() {
        return validator;
    }

    public void setValidator(OptionValidator validator) {
        this.validator = validator;
    }

    public String toString(){
        return name + ": isMandatory = " + isMandatory;
    }
}
