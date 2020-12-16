package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// @WebFilter(
//     urlPatterns = {
//         "/member/*",
//         "/anotherurl/*"
//     },
//     initParams = {
//         @WebInitParam(
//         name = "encoding", 
//         value = "UTF-8"
//         )
//     }
// )
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig arg0) throws ServletException {}
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpSession session = request.getSession();
        if (session!=null) {
            String id =(String) session.getAttribute("id");
            if (id!=null && !id.isBlank()) arg2.doFilter(arg0, arg1);
            // else arg0.getRequestDispatcher("/login/login.jsp").forward(arg0, arg1);
            else ((HttpServletResponse) arg1).sendRedirect(request.getContextPath()+"/login/login.jsp");
        }

        //TODO : session에서 memid확인, 없으면(로그인상태 아님) cookie>signed에서 memid를 얻어와서 자동로그인 수행. 그마저도 없으면 로그인페이지로
        //비밀번호찾기 페이지 등 예외처리를 해야 함
    }
}
