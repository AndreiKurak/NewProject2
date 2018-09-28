package web_app;

import common.views.*;
import framework_web.JSPView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JSPViewResolver implements ViewResolver {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private Map<String, View> viewsMap = new HashMap<String, View>(){{
        put("MessageView", new JSPView("/pages/message.jsp"));
        put("ListView", new JSPView("/pages/list.jsp"));
        put("ErrorView", new JSPView("/pages/error.jsp"));
        /*
        put("MessageView", new JSPView("/pages/message.jsp", request, response));
        put("ListView", new JSPView("/pages/list.jsp", request, response));
        put("ErrorView", new JSPView("/pages/error.jsp", request, response));
        */
    }};

    public JSPViewResolver(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public View getView(String viewName) {
        return viewsMap.get(viewName);
    }

    public void addView(String viewName, View view) {
        viewsMap.put(viewName, view);
    }

    public void setServletArguments(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
