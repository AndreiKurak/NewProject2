package commonPac.views;

import commonPac.View;

public class MessageView implements View<String> {

    public void showResult(String model){
        System.out.println(model);
    }
}
