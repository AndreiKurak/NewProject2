package web_app;

import common.ApplicationExecution;
import common.ViewModel;

import common.views.*;
import framework_web.WebPageOutput;
import framework_web.WebParametersParser;
import lib.LibraryDescriptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/librarian_request")
public class LibrarianRequestServlet extends HttpServlet {

    private ApplicationExecution applicationExecution = new ApplicationExecution();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map parameters = request.getParameterMap();

        OutputWindowViewResolver viewResolver = new OutputWindowViewResolver(new WebPageOutput(response.getWriter()));
        viewResolver.addView("MessageView", MessageView.class);
        viewResolver.addView("ListView", ListView.class);
        viewResolver.addView("ErrorView", ErrorView.class);
        
        applicationExecution.run(new WebParametersParser(parameters), new LibraryDescriptor(), viewResolver);
    }
}
