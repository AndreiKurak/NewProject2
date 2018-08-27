package framework_web;

import common.views.OutputWindowView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class WebPagePrinter implements OutputWindowView {

    PrintWriter pw;

    WebPagePrinter(HttpServletRequest request, HttpServletResponse response) {
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
