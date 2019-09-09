package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserServlet extends HttpServlet {
    private UserService service = UserService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.deleteUser(Long.parseLong(req.getParameter("id")));
            resp.setStatus(200);
            resp.sendRedirect(req.getContextPath().split("deleteUser")[0]);
        } catch (SQLException e) {
            resp.setStatus(500);
        }
    }
}
