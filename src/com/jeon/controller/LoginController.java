package com.jeon.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;

public class LoginController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", req.getParameter("id"));
        map.put("pwd", req.getParameter("pwd"));
        MemberinfoDao dao = MemberinfoDao.getInstance();
        JSONObject json = new JSONObject();
        switch (dao.getMemId(map)) {
            case -2 : 
        }
    }
}