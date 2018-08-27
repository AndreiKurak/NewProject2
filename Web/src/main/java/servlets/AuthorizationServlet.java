package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    private String login = "Andrey";
    private String password = "fk215R+L";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputLogin = request.getParameter("login");
        String inputPassword = request.getParameter("password");

        if (request.getParameter("submitPressed") != null) {
            if (request.getSession().getAttribute("login").equals(login) && request.getSession().getAttribute("password").equals(password)) {
                request.getSession().setAttribute("login", inputLogin);
                request.getSession().setAttribute("password", inputPassword);
            }
            response.sendRedirect("/start" + request.getSession().getAttribute("unreachedURL"));
        }
    }
}
