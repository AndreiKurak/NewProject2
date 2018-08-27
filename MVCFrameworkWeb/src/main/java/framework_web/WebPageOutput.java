package framework_web;

import common.views.OutputWindowView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class WebPageOutput implements OutputWindowView {

    PrintWriter pw;

    WebPageOutput(HttpServletRequest request, HttpServletResponse response) {
        try {
            pw = response.getWriter();
        }
        catch (Exception ex) {
            
        }
    }

    @Override
    public void printResult(Object object) {
        pw.println(object);
    }
}
