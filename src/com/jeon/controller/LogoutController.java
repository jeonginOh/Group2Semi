package com.jeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeon.Dao.LoginauthDao;
@WebServlet("/auth/logout.do")
public class LogoutController extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginauthDao ldao = LoginauthDao.getInstance();
        HttpSession session = req.getSession();
        int memid = Integer.parseInt((String)session.getAttribute("memid"));
        String identifier = req.getRemoteAddr()+req.getHeader("User-Agent");
        ldao.expire(memid, identifier);
        session.invalidate();
        resp.sendRedirect("/");
    }
}
