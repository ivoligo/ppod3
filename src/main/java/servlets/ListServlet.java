package servlets;

import model.User;
import service.IUserService;
import service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/list")
public class ListServlet extends HttpServlet {

IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<User> users = null;
            users = userService.getAllUser();
            req.setAttribute("users", users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/list.jsp");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/list.jsp");- вот так  не работает, ругается на /admin/admin/list.jsp
        requestDispatcher.forward(req, resp);
    }

}
