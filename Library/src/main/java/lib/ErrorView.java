package lib;

import commonPac.View;

public class ErrorView implements View {

    public void showResult(Object model){
        System.err.println(model);
    }
}
