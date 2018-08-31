package common.view_output;

import common.views.OutputWindowView;
import common.views.View;

public abstract class WebPageView {

    private OutputWindowView outputWindowView;//=new WebPageView();

    public OutputWindowView getOutputWindowView() {
        return outputWindowView;
    }

    public abstract void printResult(Object model, View view);
}
