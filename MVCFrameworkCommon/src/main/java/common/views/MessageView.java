package common.views;

public class MessageView extends View<String> {

    public MessageView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    public void showResult(String model) {
        getOutputWindowView().printResult(model);
    }
}
