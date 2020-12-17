package com.jeon.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.mail.MailSender;

import org.json.JSONObject;
@WebServlet("/member/joincheck.do")
public class MailAuthController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        Random random = new Random();
        byte[] bytes = new byte[2];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%02x",bytes[i]));
        }
        StringBuilder html = new StringBuilder();
        html.append("    <style type='text/css'>");
        html.append("        #authcode{ display: block; border:0px;  resize: none; font-size: 20px; width: fit-content;}");
        html.append("    </style>");
        html.append("    <textarea id='authcode' cols='10' rows='1' readonly >");
        html.append(sb);
        html.append("</textarea>");
        html.append("    <input type='button' value='복사' onclick='(function() {");
        html.append("            let authcode = document.getElementById('authcode');");
        html.append("            authcode.focus();");
        html.append("            authcode.select();");
        html.append("            document.execCommand('copy');}())'>");
        MailSender mail = MailSender.getInstance();
        mail.send("회원가입 이메일 인증", email, html);
        JSONObject json = new JSONObject();
        json.put("mailauthcode", sb.toString());
        resp.setContentType("text/plain;charset=utf-8");
        resp.getWriter().print(json);
    }
}