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
    final int status=1;
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
        MemberinfoVo vo = new MemberinfoVo(0, id, pwd, salt, age, email, addr, null, phone, 0, status);
        int memid = dao.insert(vo);
        if (memid>0) {
            req.getSession().setAttribute("memid", memid);
            req.getSession().setAttribute("menual", true);
            resp.sendRedirect("/");
        }
        else {
            //TODO: ERRORPAGE
        }
    }
    //join.html에서 id, phone, email 중복검사용
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        
        String type = req.getParameter("type");
        String value = req.getParameter("value");

        MemberinfoDao dao = MemberinfoDao.getInstance();

        boolean res = false;
        int n = dao.check(type, value);
        if (n<=0) res = true;
        JSONObject json = new JSONObject();
        json.put("result", res);
        resp.getWriter().print(json);
    }
}
