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

    private Map<String, JSPView> viewsMap = new HashMap<String, JSPView>(){{
        put("MessageView", new JSPView("/home", true));
        put("ListView", new JSPView("/view_pages/list.jsp"));
        put("ErrorView", new JSPView("/view_pages/error.jsp"));

        put("AddShowView", new JSPView("/options_input/add_options.jsp"));
        put("SearchShowView", new JSPView("/options_input/search_options.jsp"));
        put("UpdateShowView", new JSPView("/options_input/update_options.jsp"));
    }};

    public JSPViewResolver() { }

    public JSPViewResolver(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public View getView(String viewName) {
        return viewsMap.get(viewName);
    }

    public void addView(String viewName, JSPView view) {
        viewsMap.put(viewName, view);
    }

    public void setServletArguments(HttpServletRequest request, HttpServletResponse response) {
        viewsMap.forEach((key, value) -> {
            try {
                viewsMap.replace(key, new JSPView(value.getPageAddress(), request, response, value.isSendRedirect()));
            }
            catch (Exception ex) {
                throw new RuntimeException("Replacement failed", ex);
            }
        });
    }
}
