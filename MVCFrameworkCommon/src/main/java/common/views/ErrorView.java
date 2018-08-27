package common.views;

public class ErrorView implements View<String> {
    
    public void showResult(String model, OutputWindowView outputWindow){
        outputWindow.printResult(model);  //system.err.out
    }
}
