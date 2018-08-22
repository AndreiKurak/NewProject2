package servlets.task1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/task1")
public class SumCalculatingServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        int value1 = Integer.valueOf(req.getParameter("value1"));
        int value2 = Integer.valueOf(req.getParameter("value2"));

        PrintWriter pw = resp.getWriter();
        pw.println("<H1>Sum calculation of two numbers</H1>");
        pw.println("<H2>" + (value1 + value2) + "</H2>");
    }
}
