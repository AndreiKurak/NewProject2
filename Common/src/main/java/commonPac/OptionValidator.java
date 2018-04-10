package commonPac;

public abstract class OptionValidator {

    private OptionValidator first = this;
    private OptionValidator next;

    public OptionValidator linkWith(OptionValidator next){
        next.first = first;
        this.next = next;
        return next;
    }

    public OptionValidator getFirst() {
        return first;
    }

    public boolean isNextExist(){
        return next != null;
    }

    public abstract boolean check(String optionValue);

    public boolean checkNext(String optionValue){
        return next.check(optionValue);
    }
}
