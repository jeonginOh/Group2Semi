package yang_Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import yang_dao.BasketDao;

@WebServlet("/basketinsert.do")
public class BasketInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.setAttribute("id", "test"); //session에 id를 넣어보고 테스트(지워야함)
		String id=(String)session.getAttribute("id");
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		BasketDao dao=BasketDao.getInstance();
		int n=dao.insertDibs(id, itemid);
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("code", "success");
		}else {
			json.put("code", "fail");
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json.toString());
	}
}
