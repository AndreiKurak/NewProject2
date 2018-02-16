package commonPac;

public class OptionDescription {

    private String name;
    private boolean isMandatory;

    public OptionDescription(String name, boolean isMandatory){
        this.name = name;
        this.isMandatory = isMandatory;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMandatory(boolean isMandatory){
        this.isMandatory = isMandatory;
    }

    public String toString(){
        return name + ": isMandatory = " + isMandatory;
    }
}
