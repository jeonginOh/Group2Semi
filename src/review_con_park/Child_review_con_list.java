package review_con_park;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import itemreviewDao.Review_childDao;

import semiVo.Rev_childVo;

@WebServlet("/childlist.do")
public class Child_review_con_list extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int revid = Integer.parseInt(req.getParameter("revid"));
		Review_childDao dao = Review_childDao.getInstance();
		ArrayList<Rev_childVo> list = dao.child_list(revid);
		String userid = dao.child_list_memid(revid);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("<result>");
		
		for (Rev_childVo vo : list) {
			pw.print("<comm>");
			pw.print("<context>" + vo.getcontext_child() + "</context>");
			pw.print("<rchilddate> "+ vo.getRchilddate() + "</rchilddate>");
			pw.print("<userid>" + userid + "</userid>");
			pw.print("<rchildid>"+ vo.getRchildid()+"</rchildid>");
			pw.print("</comm>");
		}
		pw.print("</result>");
		System.out.println("테스트" + revid);
	}
}

