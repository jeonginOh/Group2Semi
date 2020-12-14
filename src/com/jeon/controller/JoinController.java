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

import semiVo.MemberinfoVo;
@WebServlet("/member/join.do")
public class JoinController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String ns_pwd = req.getParameter("pwd");
        String age = req.getParameter("birthday");
        String email = req.getParameter("email");
        String addr = req.getParameter("addr");
        String phone = req.getParameter("phone");

        MemberinfoDao dao = MemberinfoDao.getInstance();
        String salt = dao.makeSalt();
        String pwd = dao.crypt(ns_pwd, salt);
        MemberinfoVo vo = new MemberinfoVo(0, id, pwd, salt, age, email, addr, null, phone, 0, 1);
        int memid = dao.insert(vo);
        if (memid>0) {
            req.getSession().setAttribute("memid", memid);
            resp.sendRedirect("/");
        }
        else {
            //TODO: ERRORPAGE
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        HashMap<String, String> map = new HashMap<>();
        
        String id = req.getParameter("id");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        if (id!=null && !id.isBlank()) map.put("id", id);
        if (phone!=null && !phone.isBlank()) map.put("phone", phone);
        if (email!=null && !email.isBlank()) map.put("email", email);

        MemberinfoDao dao = MemberinfoDao.getInstance();

        String res = "false";
        if (map.size()>0) {
            int n = dao.check(map);
            if (n<=0) res = "true";
        }else res = "ERROR";
        JSONObject json = new JSONObject();
        json.put("result", res);
        resp.getWriter().print(json);
    }
}
