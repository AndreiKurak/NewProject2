package web_app.servlets;

import common.ApplicationExecution;

import framework_web.JSPView;
import framework_web.WebParametersParser;
import web_app.JSPViewResolver;
import web_app.WebLibraryDescriptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class LibrarianRequestServlet extends HttpServlet {

    private ApplicationExecution applicationExecution = new ApplicationExecution();

    private JSPViewResolver viewResolver = new JSPViewResolver();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        viewResolver.setServletArguments(req, resp);
        viewResolver.addView("ErrorView", new JSPView("/options_input" + req.getServletPath().
                replace(".do", "") + "_options.jsp", req, resp, false));

        applicationExecution.run(new WebParametersParser(req), new WebLibraryDescriptor(), viewResolver);
    }
}
