package common.views;

import java.util.HashMap;
import java.util.Map;

public class OutputWindowViewResolver implements ViewResolver {

    private OutputWindowView outputWindowView;
    private Map<String, View> viewMap = new HashMap<>();

    public OutputWindowViewResolver(OutputWindowView outputWindowView) {
        this.outputWindowView = outputWindowView;
    }

    @Override
    public View getView(String viewName) {
        return viewMap.getOrDefault(viewName, null);
    }

    public void addView(String viewName, Class viewClass) {
        try {
            View view = (View) viewClass.getConstructor(OutputWindowView.class).newInstance(outputWindowView);
            viewMap.put(viewName, view);
        }
        catch (Exception ex) {
            throw new RuntimeException("View adding failed", ex);
        }
    }
}
