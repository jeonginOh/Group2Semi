package yang_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yang_dao.LogisticDao;

@WebServlet("/logiChange.do")
public class Admin_LogisticChangeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int memid=Integer.parseInt(req.getParameter("memid"));
		String elogiinfo=req.getParameter("elogiinfo");
		String logiinfo=req.getParameter("logiinfo");
		String sitemid=req.getParameter("itemid");
		LogisticDao dao=LogisticDao.getInstance();
		int n=0;
		if(sitemid!=null) {
			int itemid=Integer.parseInt(sitemid);
			n=dao.update(memid, elogiinfo, logiinfo, itemid);
		}else {
			n=dao.update(memid, elogiinfo, logiinfo);
		}
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/admLogistic.do");
		}else {
			req.setAttribute("code", "오류로 인해 변경 실패!");
			req.getRequestDispatcher(req.getContextPath()+"/admLogistic.do");
		}
	}
}
