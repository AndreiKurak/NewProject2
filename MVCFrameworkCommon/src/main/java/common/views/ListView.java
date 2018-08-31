package common.views;

import java.util.Iterator;
import java.util.List;

public class ListView implements View<List> {

    private String name = "ListView";

    public void showResult(List model, OutputWindowView outputWindow){
        Iterator iterator = model.iterator();
        while (iterator.hasNext()){
            outputWindow.printResult(iterator.next());
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
