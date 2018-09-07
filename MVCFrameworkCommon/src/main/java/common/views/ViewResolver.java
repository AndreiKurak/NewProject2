package common.views;

import java.util.HashMap;
import java.util.Map;

public class ViewResolver {

    private OutputWindowView outputWindowView;

    private Map<String, View> viewsMap = new HashMap<String, View>(){{
        put("MessageView", new MessageView(outputWindowView));
        put("ListView", new ListView(outputWindowView));
        put("ErrorView", new ErrorView(outputWindowView));
    }};

    public ViewResolver(OutputWindowView outputWindowView) {
        this.outputWindowView = outputWindowView;
    }

    public View getView(String viewName) {
        return viewsMap.getOrDefault(viewName, null);
    }
    
}
