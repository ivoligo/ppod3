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

@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {

    IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        User user = null;
        try {
            user = userService.findUserById(id);
            req.setAttribute("userId", id);
            req.setAttribute("userLogin", user.getLogin());
            req.setAttribute("userAge", user.getAge());
            req.setAttribute("userCity", user.getCity());
            req.setAttribute("userPassword", user.getPassword());
            req.setAttribute("userRole", user.getRole());
            req.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        int age = Integer.parseInt(req.getParameter("age"));
        String city = req.getParameter("city");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        User user = new User(id, login, age, city, role, password);
        try {
            if (userService.findUserByLogin(user.getLogin()) == null || (userService.findUserByLogin(login).getId() == user.getId()))  {
                user.setAge(age);
                user.setPassword(password);
                user.setCity(city);
                user.setLogin(login);
                user.setRole(role);
                userService.updateUser(user);
                String path = req.getContextPath() + "/admin/list";
                resp.sendRedirect(path);
            }  else {
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter wr = resp.getWriter();
                wr.println("Пользователь с таким логином существует");
                wr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
