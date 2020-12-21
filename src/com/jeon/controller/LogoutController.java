package com.jeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeon.Dao.LoginauthDao;
@WebServlet("/auth/logout.do")
public class LogoutController extends HttpServlet{
    /**
     * session에서 memid를 가져오고 memid와 identifier가 동일한 loginauth를 폐기한다.
     * 그 후 세션을 비운다.
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginauthDao ldao = LoginauthDao.getInstance();
        HttpSession session = req.getSession();
        int memid = (int) session.getAttribute("memid");
        String identifier = req.getRemoteAddr()+req.getHeader("User-Agent");
        ldao.expire(memid, identifier);
        session.invalidate();
        //쿠키삭제
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0); 
        cookie.setPath("/");
        resp.addCookie(cookie);
        
        resp.sendRedirect(req.getHeader("referer"));
    }
}
