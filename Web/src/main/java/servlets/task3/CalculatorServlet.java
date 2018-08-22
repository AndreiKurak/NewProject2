package servlets.task3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task3_2")
public class CalculatorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int value1 = Integer.valueOf(request.getParameter("value1"));
        int value2 = Integer.valueOf(request.getParameter("value2"));

        if (request.getParameter("sumGetter") != null) {
            request.setAttribute("sum", value1 + value2);
            //request.getRequestDispatcher("/pages/task3/sum_result.jsp?sum=" + (value1 + value2)).forward(request, response);
            request.getRequestDispatcher("/pages/task3/sum_result.jsp").forward(request, response);
        }


    }
}
