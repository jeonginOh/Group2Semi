package com.jeon.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.LoginauthDao;
import com.jeon.Dao.MemberinfoDao;
import com.jeon.mail.MailSender;

import org.json.JSONObject;

import semiVo.LoginauthVo;
/**
 * loginauth 테이블에 token, memid, per=1을 설정한 데이터를 생성한다.
 */
@WebServlet("/member/findpw.do")
public class FindPwController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        String id = req.getParameter("id");
        
        MemberinfoDao dao = MemberinfoDao.getInstance();
        LoginauthDao ldao = LoginauthDao.getInstance();
        JSONObject json = new JSONObject();

        int memid = dao.getMemId(id);
        if (memid<=0) {
            json.append("find", false);
            json.append("result", "아이디를 찾을 수 없습니다.");
        }else {
            String email = dao.getEmail(memid);
            
            String token = UUID.randomUUID().toString();
            String identifier = req.getRemoteAddr()+req.getHeader("User-Agent");
            
            ldao.insert(new LoginauthVo(0, token, memid, identifier, 1, null));
            MailSender mail = MailSender.getInstance();
            StringBuilder sb = new StringBuilder();
            String URL = req.getRequestURL().toString();
            //http://http//localhost:8080/Group2Semi/member/findpw.do
            
            //localhost:8080/Group2Semi/member/findpw.do?token=80caeee7-9d93-45ee-bbd4-9e4a4d8eca1a

            String link = "?token="+token;
            SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, 1);
            String expire = sdf.format(cal.getTime());
            
            sb.append("<h1>비밀번호 변경용 링크</h1>");
            sb.append("<p>"+expire+"까지 유효합니다.</p>");
            sb.append("<a href='http://"+ URL+link +"'>변경하기</a>");
            mail.send("비밀번호찾기", email, sb);
            json.append("find", true);
            json.append("result", expire);
        }
        resp.getWriter().print(json);
    }
    /**
     * 비밀번호찾기 링크용
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginauthDao ldao = LoginauthDao.getInstance();
        // MemberinfoDao dao = MemberinfoDao.getInstance();
        //login.do?token=45be4001-0fb9-49a4-8e44-b88baea6693b
        String identifier = req.getRemoteAddr()+req.getHeader("User-Agent");

        String token = req.getParameter("token");
        int memid = ldao.templogin(token);
        ldao.renew(new LoginauthVo(0, token, memid, identifier, -1, null));//접속기록
        req.setAttribute("memid", memid);
        //TODO:비밀번호 변경 페이지
        req.getRequestDispatcher("비밀번호변경페이지").forward(req, resp);
    }
}
