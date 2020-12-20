package filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

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

public class TestFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        // if(arg0.getAttribute("aaa")==null) {
        // req.setAttribute("aaa", "filter에서 가져온 attr");
        // //req.setAttribute("ref", req.getRequestURI());
        // //req.setAttribute("method", req.getMethod());
        // System.out.println("testfilter: "+req.getParameter("text1"));
        // System.out.println(req.getMethod());
        // System.out.println(req.getRequestURL());
        // System.out.println("testfilter: "+req.getHeader("referer"));
        
        // req.getRequestDispatcher("testlogin.jsp").forward(arg0, arg1);
        // }else 
        Map<String, String[]> map= request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append("testfilter");
        for(String key : map.keySet()) {
            sb.append("\nrequest param: "+ key);
            for(String value : map.get(key)) {
                sb.append("\tvalue: "+ value);
            }
        }
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
	        for (Cookie cookie : cookies) {
	            sb.append("\ncookie: "+cookie.getName());
	            sb.append("\tvalue: "+cookie.getValue());
	        }
        }
        Enumeration<String> attrs = request.getAttributeNames();
        while(attrs.hasMoreElements()) {
            String attr = attrs.nextElement();
            sb.append("\nrequest Attribute : "+attr);
            sb.append("\tvalue: "+request.getAttribute(attr));
        }
        HttpSession session = request.getSession();
        Enumeration<String> sattrs = session.getAttributeNames();
        while(sattrs.hasMoreElements()) {
            String attr = sattrs.nextElement();
            sb.append("\nsession Attribute : "+attr);
            sb.append("\tvalue: "+session.getAttribute(attr));
        }
        System.out.println(sb.toString());
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
    
}
