package common.view_output;

import common.views.OutputWindowView;
import common.views.View;

public abstract class ConsoleView{

    private OutputWindowView outputWindowView;//= new ConsoleOutput();

    public OutputWindowView getOutputWindowView() {
        return outputWindowView;
    }

    public abstract void printResult(Object model, View view);
}
