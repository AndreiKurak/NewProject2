package common.views;

public class MessageView implements View<String> {
    
    public void showResult(String model, OutputWindowView setter) {
        setter.printResult(model);
    }
}
