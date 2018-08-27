package common.views;

public class ErrorView implements View<String> {
    
    public void showResult(String model, OutputWindowView setter){
        setter.printResult(model);  //system.err.out
    }
}
