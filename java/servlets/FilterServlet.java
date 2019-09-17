package servlets;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = UserService.getInstance().getUserByName(login);
        if (user == null) request.getRequestDispatcher("/index.jsp").forward(servletRequest, servletResponse);
        if (user.getRole().equals("admin") && user.getPassword() == password.hashCode()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
