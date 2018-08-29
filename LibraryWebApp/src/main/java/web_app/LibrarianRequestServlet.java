package web_app;

import common.ViewModel;
import common.views.ErrorView;
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
        ViewModel viewModel = applicationExecution.run(parameters, new LibraryDescriptor());

        if (viewModel.getView() != null) {
            request.setAttribute("view", viewModel);
            if (viewModel.getView().getClass().getName().contains("MessageView"))
                request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
            else if (viewModel.getView().getClass().getName().contains("ListView"))
                request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
            else
                request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        } else {
            viewModel.setModel("view не задано");
            viewModel.setView(new ErrorView());
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        }
    }
}
