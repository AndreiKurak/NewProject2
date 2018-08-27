package framework_console;

import common.views.OutputWindowView;

public class ConsoleOutput implements OutputWindowView {

    @Override
    public void printResult(Object object) {
        System.out.println(object);
    }
}
