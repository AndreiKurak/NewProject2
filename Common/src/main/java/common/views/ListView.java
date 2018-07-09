package common.views;

import java.util.Iterator;
import java.util.List;

public class ListView implements View<List> {

    public void showResult(List model){
        Iterator iterator = model.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
