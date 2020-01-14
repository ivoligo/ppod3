package servlets;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sReq, ServletResponse sResp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sReq;
        HttpServletResponse resp = (HttpServletResponse) sResp;

        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.sendRedirect("/");
        } else if (user.getRole().equalsIgnoreCase("admin")){
            filterChain.doFilter(sReq, sResp);
        } else if(user.getRole().equalsIgnoreCase("user")) {
            req.getRequestDispatcher("/user").forward(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
