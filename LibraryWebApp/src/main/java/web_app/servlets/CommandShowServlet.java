package web_app.servlets;


import framework_web.CommandShow;
import web_app.commands.AddCommandShow;
import web_app.commands.SearchCommandShow;
import web_app.commands.UpdateCommandShow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.show")
public class CommandShowServlet extends HttpServlet {

    Map<String, CommandShow> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("/add.show", new AddCommandShow());
        commands.put("/search.show", new SearchCommandShow());
        commands.put("/update.show", new UpdateCommandShow());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(commands.get(req.getServletPath()).showOptions()).forward(req,resp);
    }
}
