package framework_console;

import common.views.OutputWindowView;

public class ConsolePrinter implements OutputWindowView {

    @Override
    public void printResult(Object object) {
        System.out.println(object);
    }
}
