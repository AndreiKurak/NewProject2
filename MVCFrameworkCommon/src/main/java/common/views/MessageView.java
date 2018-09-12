package common.views;

public class MessageView extends AbstractView<String> {

    public MessageView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    public void showResult(String model) {
        outputWindowView.printResult(model);
    }
}
