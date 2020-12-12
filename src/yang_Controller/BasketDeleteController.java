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
		String aitemid=req.getParameter("itemid"); //초기 넘어온 파라미터값(1개 혹은 여러개);\
		String bd=req.getParameter("bd");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String[] sitemid=aitemid.split(","); //여러개가 넘어왔으면 콤마로 구분
		int n=0;
		for(int i=0;i<sitemid.length;i++) {
			int itemid=Integer.parseInt(sitemid[i]);
			BasketDao dao=BasketDao.getInstance();
			n+=dao.deleteDibs(id, itemid,bd);
		}
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("code", "success"+n);
		}else {
			json.put("code", "fail");
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json.toString());
	}
}
