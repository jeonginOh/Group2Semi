package review_con_park;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import itemreviewDao.ItemreviewDao;
import semiVo.ItemreviewVo;

@WebServlet("/reviewdetail")
public class Review_con_detail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		HttpSession ss=req.getSession();
		int memid=0;		
		if(ss!=null && ss.getAttribute("memid") !=null && !ss.getAttribute("memid").equals("")) {
			memid=(Integer)ss.getAttribute("memid");
		}else {
			memid=0;
		}
	
		int revid=Integer.parseInt(req.getParameter("revid"));
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		ItemreviewDao dao=ItemreviewDao.getInstance();
		
		ItemreviewVo vo=dao.review_listupdate(revid);
		String username=dao.memId(revid);
		String itemname=dao.itemname(itemid);
		int reviewcount=dao.get_revid(revid, itemid, memid);
		System.out.println(reviewcount+"리뷰갯수확인");
		req.setAttribute("revcount", reviewcount);
		req.setAttribute("itemname", itemname);
		req.setAttribute("username", username);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/jeungIn/main.jsp?spage=/parks_review/reviewdetail.jsp").forward(req, resp);
	}
	
}