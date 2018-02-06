package lib;

public class OptionDescription {

    public String name;
    boolean isMandatory;

    public OptionDescription(String name1, boolean isMandatory1){
        setName(name1);
        setMandatory(isMandatory1);
    }

    public String toString(){
        return name + ": isMandatory" + isMandatory;
    }

    public String getName(){
        return name;
    }

    public void setName(String name1){
        this.name = name1;
    }

    public void setMandatory(boolean isMandatory1){
        this.isMandatory = isMandatory1;
    }
}
