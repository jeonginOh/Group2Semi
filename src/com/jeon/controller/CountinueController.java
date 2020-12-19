package com.jeon.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/countinue")
public class CountinueController extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map= req.getParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append("countinue");
        for(String key : map.keySet()) {
            System.out.println("param: "+ key);
            sb.append("\nrequest param: "+ key);
            for(String value : map.get(key)) {
                sb.append("\tvalue: "+ value);
            }
        }
        System.out.println(sb.toString());
        System.out.println(req.getContextPath());
        String cp = req.getContextPath();
        String url = req.getParameter("ref").replace(cp, "");
        req.getRequestDispatcher(url).forward(req, resp);
        System.out.println(url);
        
    }
}
