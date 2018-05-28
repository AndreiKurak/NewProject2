package lib;

import commonPac.View;

public class SystemErrView implements View {

    public void showResult(Object model){
        System.err.println(model);
    }
}
