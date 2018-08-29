package framework_web;

import common.views.OutputWindowView;

import java.io.PrintWriter;

public class WebPageOutput implements OutputWindowView {

    PrintWriter pw;

    public WebPageOutput(PrintWriter printWriter) {
        pw = printWriter;
    }

    @Override
    public void printResult(Object object) {
        pw.println(object);
    }
}
