package servlets;

import model.User;
import service.IUserService;
import service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class FiltersServlet  extends HttpServlet {
    IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = userService.findUserByLogin(login);
            req.getSession().setAttribute("user", user);
            if (user == null){
//                resp.setContentType("text/html; charset=UTF-8");
//                PrintWriter wr = resp.getWriter();
//                wr.println("Пользователь не существует");
//                wr.close();
                req.getRequestDispatcher("/").forward(req,resp);

            } else if (user.getRole().equalsIgnoreCase("user") && user.getPassword().equals(password)){

                resp.sendRedirect("/user");
            } else if (user.getRole().equalsIgnoreCase("admin") && user.getPassword().equals(password)){
                resp.sendRedirect(req.getContextPath() + "/admin/list");
            } else{
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
