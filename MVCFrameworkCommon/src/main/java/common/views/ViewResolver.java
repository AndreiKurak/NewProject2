package common.views;

public interface ViewResolver {

    View getView(String viewName);

    void addView(String viewName, View view);
}
