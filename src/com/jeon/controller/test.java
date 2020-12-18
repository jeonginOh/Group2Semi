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
        System.out.println(req.getParameter("text"));
        System.out.println(req.getAttribute("text"));
        System.out.println(req.getAttribute("aaa"));
        req.setAttribute("bbb", "controller에서 가져온 Attr");
        System.out.println("test.do: "+req.getHeader("referer"));
        System.out.println("test.do: "+req.getRequestURL().toString());
        req.getRequestDispatcher("test.jsp").forward(req, resp);
        // resp.sendRedirect(req.getHeader("referer"));
    }
}
