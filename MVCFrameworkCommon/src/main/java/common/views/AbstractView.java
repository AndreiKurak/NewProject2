package common.views;

public abstract class AbstractView<T> implements View<T> {

    OutputWindowView outputWindowView;

    AbstractView(OutputWindowView outputWindowView){
        this.outputWindowView = outputWindowView;
    }
}
