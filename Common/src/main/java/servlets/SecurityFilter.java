package servlets;

import sun.security.util.Debug;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    private String login = "Andrey";
    private String password = "fk215R+L";
    private String wrapRequest = null;
    private boolean logIn = false;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        if (servletPath.equals("/authorization")) {
            chain.doFilter(request, response); 
            return;
        }
        
        try {
            if (request.getSession().getAttribute("login").equals(login) && request.getSession().getAttribute("password").equals(password)) {
                if (!logIn) {
                    req.getRequestDispatcher(wrapRequest).forward(req, resp);
                    logIn = true;
                }
                else
                    chain.doFilter(request, response);
            }
        }
        catch (NullPointerException ex) {
            if (request.getQueryString() != null)
                wrapRequest = request.getServletPath() + request.getQueryString();
            else
                wrapRequest = request.getServletPath();
            req.getRequestDispatcher("/pages/start.jsp").forward(req, resp);
        }
    }
}
