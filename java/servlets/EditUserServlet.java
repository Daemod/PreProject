package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditUserServlet extends HttpServlet {
    private UserService service = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(Long.parseLong(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("work"),
                Integer.parseInt(req.getParameter("age")));
        try {
            service.editUser(user.getId(), user.getName(), user.getWork(), user.getAge());
            resp.setStatus(200);
            req.setAttribute("users", service.getAllUsers());
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        } catch (SQLException e) {
            resp.setStatus(500);
        }
    }
}
