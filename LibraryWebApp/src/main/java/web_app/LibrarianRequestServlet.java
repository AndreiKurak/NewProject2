package web_app;

import common.ViewModel;
import framework_web.ApplicationExecution;
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

        ApplicationExecution applicationExecution = new ApplicationExecution();
        ViewModel viewModel = applicationExecution.run(parameters, new LibraryDescriptor(), response.getWriter());

        if (viewModel.getViewName() != null) {
            request.setAttribute("view", viewModel.getModel());

            WebPageResolver pageResolver = new WebPageResolver();
            request.getRequestDispatcher(pageResolver.getPage(viewModel.getViewName())).forward(request, response);
        } else {
            viewModel.setModel("view не задано");
            request.setAttribute("view", viewModel.getModel());

            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        }
    }
}
