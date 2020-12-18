package review_con_park;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import itemreviewDao.ItemreviewDao;
import semiVo.ItemreviewVo;

@WebServlet("/reviewdetail")
public class Review_con_detail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spageNum=req.getParameter("pageNum");
		int revid=Integer.parseInt(req.getParameter("revid"));
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		ItemreviewDao dao=ItemreviewDao.getInstance();
		
		ItemreviewVo vo=dao.review_listupdate(revid);
		String username=dao.memId(revid);
		String itemname=dao.itemname(itemid);
		req.setAttribute("itemname", itemname);
		req.setAttribute("username", username);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/parks_review/reviewdetail.jsp").forward(req, resp);
	}
	
	
}
