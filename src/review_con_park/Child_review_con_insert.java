package review_con_park;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import itemreviewDao.Review_childDao;
import semiVo.Rev_childVo;

@WebServlet("/reviewchildinsert.do")
public class Child_review_con_insert extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String context=req.getParameter("context");
		System.out.println(context);
		int revid=Integer.parseInt(req.getParameter("revid"));
		Rev_childVo vo=new Rev_childVo(0,revid,context,null);
		Review_childDao dao=Review_childDao.getInstance();
		int n=dao.review_child_insert(vo);
		resp.setContentType("text/xml; charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<result>");
		if(n>0) {
			pw.print("<code>success</code>");
		}else {
			pw.print("<code>fail</code>");
		}
		pw.print("</result>");
	}
}
