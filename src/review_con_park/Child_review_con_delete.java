package review_con_park;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import itemreviewDao.Review_childDao;

@WebServlet("/child_delete")
public class Child_review_con_delete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rchildid=Integer.parseInt(req.getParameter("rchildid"));
		Review_childDao dao=Review_childDao.getInstance();
		int n=dao.child_delete(rchildid);
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
