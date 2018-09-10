package common.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPView extends View {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public JSPView(HttpServletRequest request, HttpServletResponse response) { //String PageAddress
        this.request = request;
        this.response = response;
    }

    @Override
    public void showResult(Object model) {
        try {
            request.setAttribute("view", model);
            request.getRequestDispatcher("/pages/....jsp").forward(request, response);
        }
        catch (Exception ex) {
            
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
