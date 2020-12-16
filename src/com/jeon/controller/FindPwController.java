package com.jeon.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.LoginauthDao;
import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;

import semiVo.LoginauthVo;
/**
 * loginauth 테이블에 token, memid, per=1을 설정한 데이터를 생성한다.
 */
public class FindPwController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        String id = req.getParameter("id");
        
        MemberinfoDao dao = MemberinfoDao.getInstance();
        LoginauthDao ldao = LoginauthDao.getInstance();
        
        int memid = dao.getMemId(id);
        String token = UUID.randomUUID().toString();
        String identifier = req.getRemoteAddr()+req.getHeader("User-Agent");
        
        ldao.insert(new LoginauthVo(0, token, memid, identifier, 1, null));
        
        
        // JSONObject json = new JSONObject();
        // json.put("link", token);
    }
}
