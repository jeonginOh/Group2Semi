package com.jeon.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;
@WebServlet("/auth/tmpuser.do")
public class TemporalLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberinfoDao dao = MemberinfoDao.getInstance();
        JSONObject json = new JSONObject();
        int memid = dao.newTempUser();
        if (memid>1) {
            json.append("memid", memid);
            req.getSession().setAttribute("memid", memid);
            req.getSession().setAttribute("tempuser", true);
        }
        dao.newTempUser();
    }
}
