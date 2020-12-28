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
/* @WebFilter(
    urlPatterns = {
        "/member/*",
        "/anotherurl/*"
    },
    initParams = {
        @WebInitParam(
        name = "encoding", 
        value = "UTF-8"
        )
    }
) */
    /**
     * referer를 통한 파라미터 전달법
     * 흐름 : 요청->(인터럽트 발생--| 필터->로그인.jsp->Controller |--)->목적지.jsp
     * 1. GET방식이어야 한다.
     * 2. controller에서 req.getHeader("referer")를 하게 되면 
     *    http://localhost:8080/Group2Semi/test/test.jsp?text=qwer
     *    이런 식으로 결과가 나온다.
     * 3. login.html의 ajax에서 location.href를 수정하면?
     * 이거못써먹음 포기
     */
    /**
     * 방법2
     * 로그인페이지(jsp or controller?에서 모든 파라미터를 세션에 저장한다.
     * 	Map<String, String[]> map= request.getParameterMap();
        for(String key : map.keySet()) {
            System.out.println("key: "+ key);
            for(String value : map.get(key)) {
                System.out.println("value: "+ value);
            }
        }
     * 그 후 필터에서 로그인페이지가 아닌 곳에서는 session을 request로 변환하고 전달하는 session attr을 제거한다.
     * 흐름 : 요청->(로그인필터(param->session + 요청방식) 
     * -->(로그인.jsp-
     * 
     * 
     * >Controller) |--->세션확인필터(session 제거, req attr)) 
     * -> 목적지.jsp/controller
     * req.getMethod()->방식 알아오기
     * req.getParameter
     * req.getAttr... 
     */
     /**
      * 방법 3
      * 방법 2에서 로그인페이지에서 로그인 성공하면 새로운 controller를 통해서 forward
      * 요청->로그인필터->전달용컨트롤러->요청목적지
      변경
        방식 : req.getMethod()
        목적지 : req.getRequestURL()
      */
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

        arg0.setAttribute("ref", request.getHeader("referer"));
        arg0.setAttribute("method", request.getMethod());
        arg0.setAttribute("url", request.getRequestURI());
        System.out.println("LOGINFILTER:"+request.getRequestURI().replace(request.getContextPath(), ""));
        // -> /user/editinfo.jsp
        // -> /user/myinfo.do
        String FILTEREDURL = request.getRequestURI().replace(request.getContextPath(), "");

        if (FILTEREDURL.equals("recentShop.jsp")) arg2.doFilter(arg0, arg1);


        //session에서 memid확인
        if (session.getAttribute("memid")!=null) {
            if (session.getAttribute("menual")!=null && (boolean) session.getAttribute("menual")) {
                //수동 로그인일 경우 진행
                arg2.doFilter(arg0, arg1);
            } else if (session.getAttribute("tempuser")!=null && (boolean) session.getAttribute("tempuser")) {
                //임시회원일 경우
                // 임시회원 로그인 기능
                //arg0.getRequestDispatcher("/auth/login.jsp").forward(arg0, arg1);
                if (FILTEREDURL.equals("/logistic.do")) { 
                    // arg0.getRequestDispatcher("/auth/login.jsp").forward(arg0, arg1);
                } else if (FILTEREDURL.equals("/logistic.do")) {
                    // arg0.getRequestDispatcher("/auth/login.jsp").forward(arg0, arg1);
                } else arg2.doFilter(arg0, arg1);
            } else if (session.getAttribute("admin")!=null && (boolean) session.getAttribute("admin")) {
                //관리자일 경우
                arg2.doFilter(arg0, arg1);
            } else {
                //자동 로그인일 경우
                //url에 따라서 로그인 페이지 요청
                // if (filteredurl.equals("")) {

                // }
                arg2.doFilter(arg0, arg1);
            }
        }
        //없으면(로그인상태 아님) 
        else{
        	System.out.println("loginfilter");
            Cookie[] cookies = request.getCookies();
            if (cookies!=null) {
                //cookie>token에서 memid를 얻어와서 자동로그인 수행
            	boolean found=false;
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")) {
                    	found=true;
                    	System.out.println("token found");
                        String token = cookie.getValue();
                        String identifier = request.getRemoteAddr()+request.getHeader("User-Agent");
                        LoginauthDao ldao = LoginauthDao.getInstance();
                        int memid = ldao.autologin(token, identifier);
                        System.out.println("memid"+memid);
                        System.out.println("token"+token);
                        System.out.println("identifier"+identifier);
                        if (memid>0) {
                            System.out.println("autologin success, new cookie");
                            session.setAttribute("memid", memid);
                            //테이블, 쿠키 갱신. 로그인 진행
                            Cookie nck = new Cookie("token", ldao.renew(new LoginauthVo(0, token, memid, identifier, 0, null)));
                            nck.setMaxAge(60*60*24*expiredate);
                            nck.setPath("/");
                            response.addCookie(nck);
                            arg2.doFilter(arg0, arg1);
                        }else if(memid==0) {//다시 로그인해야함
                            // response.sendRedirect(request.getContextPath()+"/auth/login.html");
                        }else if(memid==-1) {
                            ldao.expireAll(token);
                            // response.sendRedirect(request.getContextPath()+"/auth/loginError.html");
                        }
                        arg0.getRequestDispatcher("/auth/login.jsp").forward(arg0, arg1);
                    }
                }
                if (!found) arg0.getRequestDispatcher("/auth/login.jsp").forward(arg0, arg1);
            }else arg0.getRequestDispatcher("/auth/login.jsp").forward(arg0, arg1);
        }
    }
}
