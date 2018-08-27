package common.views;

public class MessageView implements View<String> {
    
    public void showResult(String model, OutputWindowView outputWindow) {
        outputWindow.printResult(model);
    }
}
