package lib;

import commonPac.View;

public class ListView implements View {

    public void showResult(Object model){
        System.out.println("List of books or it's parameters:\n" + model);
    }
}
