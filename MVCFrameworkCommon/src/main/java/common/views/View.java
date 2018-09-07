package common.views;

import javax.servlet.http.HttpServletRequest;

public abstract class View<T> {

    private OutputWindowView outputWindowView;

    View() { }
  
    View(OutputWindowView outputWindowView){
        this.outputWindowView = outputWindowView;
    }

    public abstract void showResult(T model);

    OutputWindowView getOutputWindowView() {
        return outputWindowView;
    }
  /*
    private HttpServletRequest request;
    private HttpServletResponse response;

    View(String jspAddress, HttpServletRequest request, HttpServletResponse response) {  }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpSevletResponse getResponse() {
        return response;
    }
    */
}
