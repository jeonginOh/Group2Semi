package review_con_park;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import itemreviewDao.ItemreviewDao;
@WebServlet("/reviewdelete")
public class Review_con_delete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int revid=Integer.parseInt(req.getParameter("revid"));
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		
		ItemreviewDao dao=ItemreviewDao.getInstance();
		int n=dao.review_delete(revid);
		if (n > 0) {
			req.setAttribute("code", "success");
			req.setAttribute("itemid", itemid);
			req.getRequestDispatcher("/parks_review/insertsucc.jsp").forward(req, resp);
		} else {
			req.setAttribute("code", "fail");
			req.setAttribute("itemid", itemid);
			req.getRequestDispatcher("/parks_review/insertsucc.jsp").forward(req, resp);
		}
	
	}
	
}
