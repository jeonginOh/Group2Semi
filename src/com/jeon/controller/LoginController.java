package com.jeon.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;
@WebServlet("/login/login.do")
public class LoginController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", req.getParameter("id"));
        map.put("pwd", req.getParameter("pwd"));
        MemberinfoDao dao = MemberinfoDao.getInstance();
        JSONObject json = new JSONObject();
        int memid = dao.getMemId(map);
        switch (memid) {
            case -2 : json.append("errMsg", "에러가 발생했습니다."); break;
            case -1 : json.append("errMsg", "아이디가 존재하지 않습니다."); break;
            case 0 : json.append("errMsg", "아이디와 비밀번호가 맞지 않습니다."); break;
            default : {
                json.append("memid", memid);
                req.getSession().setAttribute("id", memid);
            }
        }
        resp.getWriter().print(json);
    }
}