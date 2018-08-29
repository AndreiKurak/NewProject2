package common.views;

public abstract class AbstractView<T> {

    public OutputWindowView outputWindow;

    public abstract void showResult(T object);

    public OutputWindowView getOutputWindow() {
        return outputWindow;
    }

    public void setOutputWindow(OutputWindowView outputWindow) {
        this.outputWindow = outputWindow;
    }
}

/*
public class MessageView2 extends ConsoleView<String> {

    @Override
    public void showResult(String object) {
        getOutputWindow().printResult(object);
    }
}
 */

/*
в main:
    .setOutputWindow(new ConsoleOutput);
    viewModel.getView().showResult(viewModel.getModel(), new ConsoleOutput());
 */
