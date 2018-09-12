package web_app;

import common.views.*;

import java.util.HashMap;
import java.util.Map;

public class WebViewResolver implements ViewResolver {

    private OutputWindowView outputWindowView;

    private Map<String, View> viewsMap = new HashMap<String, View>(){{
        put("MessageView", new MessageView(outputWindowView));
        put("ListView", new ListView(outputWindowView));
        put("ErrorView", new ErrorView(outputWindowView));
    }};

    public WebViewResolver(OutputWindowView outputWindowView) {
        this.outputWindowView = outputWindowView;
    }

    public View getView(String viewName) {
        return viewsMap.getOrDefault(viewName, null);
    }

    public void addView(String viewName, View view) {
        viewsMap.put(viewName, view);
    }
}
