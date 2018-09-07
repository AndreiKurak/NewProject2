package common.views;

import java.util.Iterator;
import java.util.List;

public class ListView extends View<List> {

    public ListView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    public void showResult(List model){
        Iterator iterator = model.iterator();
        while (iterator.hasNext()){
            getOutputWindowView().printResult(model);
        }
    }
}
