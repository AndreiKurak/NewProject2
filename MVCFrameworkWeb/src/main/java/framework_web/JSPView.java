package framework_web;

import common.views.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPView implements View {

    private String pageAddress;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private boolean sendRedirect = false;

    public JSPView(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    public JSPView(String pageAddress, boolean sendRedirect) {
        this.pageAddress = pageAddress;
        this.sendRedirect = sendRedirect;
    }

    public JSPView(String pageAddress, HttpServletRequest request, HttpServletResponse response, boolean sendRedirect) {
        this.pageAddress = pageAddress;
        this.request = request;
        this.response = response;
        this.sendRedirect = sendRedirect;
    }

    public String getPageAddress() {
        return pageAddress;
    }

    public boolean isSendRedirect() {
        return sendRedirect;
    }

    @Override
    public void showResult(Object model) {
        try {
            //request.setAttribute("view", model);
            //request.getRequestDispatcher(pageAddress).forward(request, response);
            request.getSession().setAttribute("view", model);
            if (sendRedirect) {
                System.out.println("bla bla");
                //request.getSession().setAttribute("view", model);
                response.sendRedirect(request.getContextPath() + pageAddress);
            }
            else {
                //request.setAttribute("view", model);
                request.getRequestDispatcher(pageAddress).forward(request, response);
            }
        }
        catch (Exception ex) {
            throw new RuntimeException("JSPView failure using request", ex);
        }
    }
}
