package web_app;

import common.ApplicationExecution;
import common.ViewModel;

import common.views.ErrorView;
import common.views.ListView;
import common.views.MessageView;
import common.views.ViewResolver;
import framework_web.WebPageOutput;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map parameters = request.getParameterMap();
        String[] query = request.getParameter("inputQuery").split(" ");
       /*
        ViewResolver viewResolver = new WebViewResolver(new WebPageOutput(response.getWriter()));
        viewResolver.addView("MessageView", new MessageView(new WebPageOutput(response.getWriter())));
        viewResolver.addView("ListView", new ListView(new WebPageOutput(response.getWriter())));
        viewResolver.addView("ErrorView", new ErrorView(new WebPageOutput(response.getWriter())));
         */
        ApplicationExecution applicationExecution = new ApplicationExecution();
        applicationExecution.run(query, new LibraryDescriptor(), new WebViewResolver(new WebPageOutput(response.getWriter())));
    }
}
