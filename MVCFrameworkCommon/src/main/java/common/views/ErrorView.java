package common.views;

public class ErrorView extends AbstractView<String> {

    public ErrorView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    public void showResult(String model){
        outputWindowView.printResult(model);   //system.err.out
    }
}
