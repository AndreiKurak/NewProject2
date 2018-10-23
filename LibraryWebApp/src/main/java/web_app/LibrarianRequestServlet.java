package web_app;

import common.ApplicationExecution;

import common.ViewModel;
import framework_web.WebParametersParser;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher( "/options_input" + req.getServletPath().
            replace(".do", "") + "_options.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        viewResolver.setServletArguments(request, response);
        applicationExecution.run(new WebParametersParser(request), new WebLibraryDescriptor(), viewResolver);
    }
}
