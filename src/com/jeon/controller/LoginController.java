package com.jeon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.LoginauthDao;
import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;

import semiVo.LoginauthVo;
import semiVo.MemberinfoVo;
@WebServlet("/auth/login.do")
public class LoginController extends HttpServlet{
    final int expiredate=7;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        HashMap<String, String> map = new HashMap<>();
        // System.out.println((String) req.getParameter("id"));
        map.put("id", req.getParameter("id"));
        map.put("pwd", req.getParameter("pwd"));
        System.out.println("LOGIN:ID="+req.getParameter("id"));
        System.out.println("LOGIN:PWD="+req.getParameter("pwd"));
        MemberinfoDao dao = MemberinfoDao.getInstance();
        LoginauthDao ldao = LoginauthDao.getInstance();
        JSONObject json = new JSONObject();
        int memid = dao.login(map);
        
        //자동로그인 등록
        //uuid생성, 쿠키에 signed : uuid 생성, db에 uuid 저장
        if (memid<=0) {
            System.out.println("error"+ memid);
            json.append("error", true);
            json.append("errMsg", memid);
        }else { 
            System.out.println("noerror");
            boolean autologin = Boolean.parseBoolean(req.getParameter("autologin"));
            //로그인성공 상황 : session에 memid가 없고 cookie에도 memid가 없음. 
            String token = UUID.randomUUID().toString();
            //자동로그인
            if (autologin) {
                //쿠키생성
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(60*60*24*expiredate);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }else {
                //쿠키삭제
                Cookie cookie = new Cookie("token", "");
                cookie.setMaxAge(0); 
                cookie.setPath("/");
                resp.addCookie(cookie);
                //테이블삭제 - 사실 삭제는 아니고 사용불가처리
                // ldao.expire
                //필요없음 - 쿠키가 없으니 접근할 수 없고 언젠가 알아서 지워질 것.
            }
            //테이블생성
            String identifier = req.getRemoteAddr()+req.getHeader("User-Agent");
            int n = ldao.insert(new LoginauthVo(0, token, memid, identifier, 0, null));
            System.out.println(n);
            json.append("error", false);
            json.append("value", memid);
            req.setAttribute("method", req.getMethod());
            req.setAttribute("ref", req.getRequestURI());
            req.getSession().setAttribute("memid", memid);
            //이 세션에 수동로그인 했다는 흔적을 남김
            req.getSession().setAttribute("menual", true);
        }
        resp.getWriter().print(json);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        JSONObject json = new JSONObject();
        MemberinfoDao dao = MemberinfoDao.getInstance();
        LoginauthDao ldao = LoginauthDao.getInstance();

        MemberinfoVo vo = new MemberinfoVo(0, null, null, null, null, null, null, null, null, 0, 3);
        //TODO: 비회원을 등록하고 memid를 session에 넣는다.
    }
}