package commonPac.views;

import commonPac.View;

public class ErrorView implements View<String> {

    public void showResult(String model){
        System.err.println(model);
    }
}
