package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeon.Dao.LoginauthDao;

import semiVo.LoginauthVo;
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
        arg0.setCharacterEncoding("UTF-8");
        final int expiredate = 7;
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();

        String s_memid = (String) session.getAttribute("memid");
        //session에서 memid확인
        if (s_memid!=null && !s_memid.isBlank()) arg2.doFilter(arg0, arg1);
        //없으면(로그인상태 아님) 
        else{
            Cookie[] cookies = request.getCookies();
            if (cookies!=null) {
                //cookie>token에서 memid를 얻어와서 자동로그인 수행
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        String identifier = request.getRemoteAddr()+request.getHeader("User-Agent");
                        LoginauthDao ldao = LoginauthDao.getInstance();
                        int memid = ldao.autologin(token, identifier);
                        if(memid==0) {//다시 로그인
                            response.sendRedirect(request.getContextPath()+"/auth/login.html");
                        }else if(memid==-1) {
                            //쿠키유출
                            //사용자에게 경고해야함
                            response.sendRedirect(request.getContextPath()+"/auth/loginError.html");
                        }else {
                            //테이블, 쿠키 갱신
                            cookie.setValue(ldao.renew(new LoginauthVo(0, token, memid, identifier, 0, null)));
                            cookie.setMaxAge(60*60*24*expiredate);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                        }
                    }
                }
            }
        }
        arg2.doFilter(arg0, arg1);
    }
}
