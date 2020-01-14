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
import java.io.PrintWriter;

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {

    IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        int age = Integer.parseInt(req.getParameter("age"));
        String city = req.getParameter("city");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        User user = new User(login, age, city, password);

        try {
            if( ( userService.findUserByLogin(login) == null || !login.equalsIgnoreCase(userService.findUserByLogin(login).getLogin())) ){
                userService.AddUser(user);
                String path = req.getContextPath() + "/admin/list";
                resp.sendRedirect(path);
            } else {
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter wr = resp.getWriter();
                wr.println("Пользователь существует");
                wr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
