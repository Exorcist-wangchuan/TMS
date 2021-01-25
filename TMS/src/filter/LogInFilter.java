package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInFilter implements Filter {
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Access Filter executed!");
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null && request.getRequestURI().indexOf("login.jsp") == -1 ) {
            System.out.println(session.getAttribute("user"));
            //判断是否是login页面，如果是login页面的话没有request，session为空会出现错误
            response.sendRedirect("/TMS/login.jsp");
            return;
        }
        filterChain.doFilter(arg0, arg1);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
