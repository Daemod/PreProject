package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class NewUserServlet extends HttpServlet {
    private UserService service = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.addUser(req.getParameter("name"),
                    req.getParameter("work"),
                    Integer.parseInt(req.getParameter("age")));
            resp.setStatus(200);
            req.setAttribute("users", service.getAllUsers());
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        } catch (SQLException e) {
            resp.setStatus(500);
        }
    }
}
