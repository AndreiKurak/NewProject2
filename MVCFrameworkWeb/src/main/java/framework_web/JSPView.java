package framework_web;

import common.views.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPView implements View {

    private String pageAddress;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public JSPView(String pageAddress, HttpServletRequest request, HttpServletResponse response) {
        this.pageAddress = pageAddress;
        this.request = request;
        this.response = response;
    }

    @Override
    public void showResult(Object model) {
        try {
            request.setAttribute("view", model);
            request.getRequestDispatcher(pageAddress).forward(request, response);
        }
        catch (Exception ex) {
            throw new RuntimeException("JSPView failure using request", ex);
        }
    }

    public String getPageAddress() {
        return pageAddress;
    }
}
