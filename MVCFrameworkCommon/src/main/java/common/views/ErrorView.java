package common.views;

public class ErrorView extends View<String> {

    public ErrorView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    public void showResult(String model){
        getOutputWindowView().printResult(model);   //system.err.out
    }
}
