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

@WebServlet("/basketdelete.do")
public class BasketDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		BasketDao dao=BasketDao.getInstance();
		int n=dao.deleteDibs(id, itemid);
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
