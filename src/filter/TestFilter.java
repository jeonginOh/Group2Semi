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

public class TestFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpServletResponse resp = (HttpServletResponse) arg1;
        if(arg0.getAttribute("aaa")==null) {
        req.setAttribute("aaa", "filter에서 가져온 attr");
        System.out.println("testfilter: "+req.getParameter("text"));
        System.out.println("testfilter: "+req.getHeader("referer"));
        req.getRequestDispatcher("testlogin.jsp").forward(arg0, arg1);
        }else doFilter(arg0, arg1, arg2);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
    
}
