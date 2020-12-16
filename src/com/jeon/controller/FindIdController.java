package com.jeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;
@WebServlet("/member/findid.do")
public class FindIdController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        String target = req.getParameter("target");
        String value = req.getParameter("value");
        MemberinfoDao dao = MemberinfoDao.getInstance();
        String result = dao.findId(target, value);
        JSONObject json = new JSONObject();
        if (result==null || result.length()<6) {
            json.put("find", false);
            json.put("result", "아이디를 찾을 수 없습니다.");
        }else {
            json.put("find", true);
            json.put("result", result);
        }
        resp.getWriter().print(json);
    }
}
