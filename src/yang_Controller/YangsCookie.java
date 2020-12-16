package yang_Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

//이 페이지는 쿠키 예시페이지입니다. 실제로 사용되는 페이지가 아닙니다.
public class YangsCookie extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemid=req.getParameter("itemid");
		Cookie cookie=new Cookie("itemid", itemid);
		cookie.setMaxAge(60*60*24); //쿠키 시간은 24시간 설정
		cookie.setPath("/");
		resp.addCookie(cookie);
		
		JSONObject json=new JSONObject();
		json.put("code", "success");
		resp.setCharacterEncoding("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
