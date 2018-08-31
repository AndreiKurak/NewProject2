package common.views;

public class MessageView implements View<String> {

    private String name = "MessageView";

    public void showResult(String model, OutputWindowView outputWindow) {
        outputWindow.printResult(model);

    }

    @Override
    public String getName() {
        return name;
    }
}
