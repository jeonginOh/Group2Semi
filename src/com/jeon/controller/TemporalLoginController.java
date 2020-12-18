package com.jeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/auth/nonuser.do")
public class TemporalLoginController extends HttpServlet {
    /**
     * referer를 통한 파라미터 전달법
     * 흐름 : 요청->(인터럽트 발생--| 필터->로그인.jsp->Controller |--)->목적지.jsp
     * 1. GET방식이어야 한다.
     * 2. controller에서 req.getHeader("referer")를 하게 되면 
     *    http://localhost:8080/Group2Semi/test/test.jsp?text=qwer
     *    이런 식으로 결과가 나온다.
     * 3. login.html의 ajax에서 location.href를 수정하면?
     * 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}
