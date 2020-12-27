package com.jeon.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;


@WebServlet("/admin/memberlist.edit")
public class MemberlistBatchEdit extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        JSONObject json = new JSONObject();
        MemberinfoDao dao = MemberinfoDao.getInstance();
        String type = req.getParameter("type");
        String value = req.getParameter("value");
        System.out.println(value.startsWith("+"));
        String[] memids = req.getParameterValues("memids");
        json.append("result", dao.batchChange(type, value, memids));
        resp.getWriter().print(json);
    }
}
