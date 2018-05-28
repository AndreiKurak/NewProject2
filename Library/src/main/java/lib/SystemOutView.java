package lib;

import commonPac.View;

public class SystemOutView implements View {

    public void showResult(Object model){
        System.out.println(model);
    }
}
