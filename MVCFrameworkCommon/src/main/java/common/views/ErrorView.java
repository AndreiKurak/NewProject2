package common.views;

public class ErrorView implements View<String> {

    private String name = "ErrorView";

    public void showResult(String model, OutputWindowView outputWindow){
        outputWindow.printResult(model);  //system.err.out
    }

    @Override
    public String getName() {
        return name;
    }
}
