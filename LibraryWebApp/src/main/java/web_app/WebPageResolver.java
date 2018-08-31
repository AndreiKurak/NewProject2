package web_app;

import java.util.HashMap;
import java.util.Map;

public class WebPageResolver {

    private Map<String, String> viewsMap = new HashMap<String, String>(){{
        put("MessageView", "/pages/message.jsp");
        put("ListView", "/pages/list.jsp");
        put("ErrorView", "/pages/error.jsp");
    }};

    public String getPage(String viewName) {
        return viewsMap.getOrDefault(viewName, null);
    }
}
