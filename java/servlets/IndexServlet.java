package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password.hashCode());
        try {
            User user = UserService.getInstance().getUserByName(login);
            if (user == null) resp.getOutputStream().print("ERROR auth");
            if (user.getRole().contains("admin") && user.getPassword() == password.hashCode()) {
                req.setAttribute("users", UserService.getInstance().getAllUsers());
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
            } else if (user.getRole().contains("user") && user.getPassword() == password.hashCode()) {
                req.getRequestDispatcher("/user.html").forward(req, resp);
            } else {
                resp.getOutputStream().print("You not have admin permission");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
