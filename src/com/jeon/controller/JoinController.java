package com.jeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import semiVo.MemberinfoVo;

public class JoinController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String age = req.getParameter("age");
        String email = req.getParameter("email");
        String addr = req.getParameter("addr");
        String phone = req.getParameter("phone");

        MemberinfoDao dao = MemberinfoDao.getInstance();
        MemberinfoVo vo = new MemberinfoVo(0, id, pwd, dao.makeSalt(), age, email, addr, null, phone, 0, 0);

    }
}
