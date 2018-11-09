package web_app.servlets;

import lib.PropertyValuesGetter;
import lib.connectors.DataConnectionException;
import lib.connectors.SessionFactoryGetter;
import lib.library_entities.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            SessionFactoryGetter.setSessionFactory(new Configuration().
                    addPackage("lib.library_entities").
                    addProperties(new PropertyValuesGetter().getProp()).
                    addAnnotatedClass(Book.class).buildSessionFactory());
            Class.forName("com.mysql.cj.jdbc.Driver");  //Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex) {
            throw new DataConnectionException("jdbc driver registration failed", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/home");
    }

    @Override
    public void destroy() {
        super.destroy();
        SessionFactoryGetter.getSessionFactory().close();
    }
}
