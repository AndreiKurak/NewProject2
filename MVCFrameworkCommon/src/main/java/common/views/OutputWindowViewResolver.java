package common.views;

import java.util.HashMap;
import java.util.Map;

public class OutputWindowViewResolver implements ViewResolver {

    private OutputWindowView outputWindowView;
    private Map<String, View> viewMap = new HashMap<>();

    public OutputWindowViewResolver() { }

    public OutputWindowViewResolver(OutputWindowView outputWindowView) {
        this.outputWindowView = outputWindowView;
    }

    @Override
    public View getView(String viewName) {
        return viewMap.get(viewName);
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

    public void setOutputWindowView(OutputWindowView outputWindowView) {
        this.outputWindowView = outputWindowView;
        viewMap.forEach((key, value) -> {
            try {
                viewMap.replace(key, value.getClass().getConstructor(OutputWindowView.class).newInstance(outputWindowView));
            }
            catch (Exception ex) {
                throw new RuntimeException("Replacement failed", ex);
            }
        });
    }
}
