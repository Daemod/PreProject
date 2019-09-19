package servlets;

import model.User;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) servletRequest.getAttribute("user");

        if (user != null && user.getRole().contains("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/");
        }


    }

    @Override
    public void destroy() {

    }
}
