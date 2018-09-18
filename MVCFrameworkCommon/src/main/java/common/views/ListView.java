package common.views;

import java.util.Iterator;
import java.util.List;

public class ListView extends AbstractView<List> {

    public ListView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    public void showResult(List model){
        Iterator iterator = model.iterator();
        while (iterator.hasNext()){
            outputWindowView.printResult(iterator.next());
        }
    }
}
