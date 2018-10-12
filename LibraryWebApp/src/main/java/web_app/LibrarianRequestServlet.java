package web_app;

import common.ApplicationExecution;

import framework_web.WebParametersParser;
import lib.LibraryDescriptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/librarian_request")
public class LibrarianRequestServlet extends HttpServlet {
    
    private ApplicationExecution applicationExecution = new ApplicationExecution();
    //private OutputWindowViewResolver viewResolver = new OutputWindowViewResolver();
    private JSPViewResolver viewResolver = new JSPViewResolver();

    @Override
    public void init() throws ServletException {
        super.init();
        /*
        viewResolver.addView("MessageView", MessageView.class);
        viewResolver.addView("ListView", ListView.class);
        viewResolver.addView("ErrorView", ErrorView.class);
        */
    }        

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //viewResolver.setOutputWindowView(new WebPageOutput(response.getWriter()));
        viewResolver.setServletArguments(request, response);
        applicationExecution.run(new WebParametersParser(request.getParameterMap()), new WebLibraryDescriptor(), viewResolver);
    }
}
