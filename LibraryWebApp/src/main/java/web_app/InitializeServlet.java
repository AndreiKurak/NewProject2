package web_app;

import lib.connectors.DataConnectionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/letsRock")
public class InitializeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex) {
            throw new DataConnectionException("jdbc driver registration failed", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/start_page.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
