package framework_console;

import common.view_output.ConsoleView;
import common.views.View;

public class ConsoleOutputView extends ConsoleView {
    
    @Override
    public void printResult(Object model, View view) {
        view.showResult(model, new ConsoleOutput()); //getOutputWindowView());
    }
}
