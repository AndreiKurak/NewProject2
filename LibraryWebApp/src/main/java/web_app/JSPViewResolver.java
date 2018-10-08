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
        put("MessageView", new JSPView("/view_pages/message.jsp"));
        put("ListView", new JSPView("/view_pages/list.jsp"));
        put("ErrorView", new JSPView("/view_pages/error.jsp"));
    }};

    public JSPViewResolver() { }

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
        viewsMap.forEach((key, value) -> {
            try {
                viewsMap.replace(key, new JSPView(((JSPView)value).getPageAddress(), request, response));
            }
            catch (Exception ex) {
                throw new RuntimeException("Replacement failed", ex);
            }
        });
    }
}
