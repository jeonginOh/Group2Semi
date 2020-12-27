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

import com.jeon.Dao.MemberinfoDao;

public class AdminLoginFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpServletResponse resp = (HttpServletResponse) arg1;
        HttpSession session = req.getSession();
        MemberinfoDao dao = MemberinfoDao.getInstance();
        if (session!=null && session.getAttribute("memid")!=null) {
            int memid = (int) session.getAttribute("memid");
            if (session.getAttribute("admin")!=null) {
            // || !(boolean)session.getAttribute("admin")) {
                session.setAttribute("admin", dao.isAdmin(memid));
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}
    
}
