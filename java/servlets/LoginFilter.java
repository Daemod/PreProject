package servlets;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = UserService.getInstance().getUserByName(login);
        if (user != null && user.getPassword() == password.hashCode()) {
            servletRequest.setAttribute("user", user);
            filterChain.doFilter(servletRequest, servletResponse);
        } else request.getRequestDispatcher("/index.jsp").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
