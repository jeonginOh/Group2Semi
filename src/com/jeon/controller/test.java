package com.jeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/test/test.do")
public class test extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //String memid = (String) session.getAttribute("memid");
        resp.getWriter().print(session.getAttribute("memid")+"\n");
        resp.getWriter().print(session.getAttribute("menual")+"\n");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            resp.getWriter().print(cookie.getName()+"\n");
            resp.getWriter().print(cookie.getValue()+"\n");
        }
    }
}
