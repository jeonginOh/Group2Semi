package com.jeon.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;

import semiVo.MemberinfoVo;

@WebServlet("/user/myinfo.do")
public class MyInfoEditController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject json = null;
        MemberinfoDao dao = MemberinfoDao.getInstance();
        if (req.getParameter("memid")==null) {
            json = dao.getVo((int) req.getSession().getAttribute("memid")).toJson();
        }else json = dao.getVo(Integer.parseInt(req.getParameter("memid"))).toJson();
        resp.setContentType("text/plain;charset=utf-8");
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        String newpwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        String addr = req.getParameter("addr");
        String phone = req.getParameter("phone");
        int memid = (int) req.getSession().getAttribute("memid");
        MemberinfoDao dao = MemberinfoDao.getInstance();
        MemberinfoVo vo = dao.getVo(memid);
        boolean check1=true;
        boolean check2=true;
        JSONObject json = new JSONObject();
        /**
         * 비었거나 null이면 변경하지 않는다. 
         */

        if (newpwd!=null && !newpwd.isBlank()) {
            check1 = dao.newPwd(memid, newpwd);
        }

        if (email!=null && !email.isBlank()) {
            vo.setEmail(email);
        }
        if (addr!=null && !addr.isBlank()) {
            vo.setAddr(addr);
        }
        if (phone!=null && !phone.isBlank()) {
            vo.setPhone(phone);
        }
        check2 = dao.change(vo);
        json.append("result", (check1 && check2));
        resp.getWriter().print(json);
    }
}
