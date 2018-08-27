package web_app;

import framework_web.ApplicationExecution;
import lib.LibraryDescriptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/librarian_request")
public class LibrarianRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] query = request.getParameter("inputQuery").split(" ");

        if (request.getParameter("submitPressed") != null) {
            //ApplicationExecution applicationExecution = new ApplicationExecution();
            //applicationExecution.run(query, new LibraryDescriptor(), request, response);
            request.getSession().setAttribute("query", query);
            request.getSession().setAttribute("libDescriptor", new LibraryDescriptor());

            //request.getRequestDispatcher("/view_servlet").forward(request, response);
            response.sendRedirect("/common/view_servlet");
        }
    }
}
