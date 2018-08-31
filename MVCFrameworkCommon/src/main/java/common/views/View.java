package common.views;

public interface View<T> {

    String getName();

    void showResult(T model, OutputWindowView outputWindow);
}
