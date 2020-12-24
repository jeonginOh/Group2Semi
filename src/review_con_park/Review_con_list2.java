package review_con_park;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import itemreviewDao.ItemreviewDao;
import ohDao.iteminfoDao;
import semiVo.ItemreviewVo;

@WebServlet("/reviewlist2.do")
public class Review_con_list2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss=req.getSession();
		String memid1=(String)ss.getAttribute("memid");
		int memid=0;		
		if(memid1==null ) {
			memid=15616589;
		}else {
			memid=Integer.parseInt(memid1);
		}
		
		req.setCharacterEncoding("utf-8");
		int itemid = Integer.parseInt(req.getParameter("itemid"));
		if(itemid==0) {
			itemid=1;
		}
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;
		ItemreviewDao dao = ItemreviewDao.getInstance();
		double ave = dao.avestar(itemid);
		int buycount=dao.get_buyid(itemid, memid);
		String itemname = dao.itemname(itemid);
		ArrayList<String> username = dao.userName(memid);
		ArrayList<ItemreviewVo> list = dao.review_list2(itemid, startRow, endRow);
		int pageCount = (int) Math.ceil(dao.getCount(itemid) / 10.0);
		int startPageNum = (pageNum - 1) / 10 * 10 + 1;
		int endPageNum = startPageNum + 9;
		if (endPageNum > pageCount) {
			endPageNum = pageCount;
		}
		req.setAttribute("buycount", buycount);
		req.setAttribute("itemname", itemname);
		req.setAttribute("username", username);
		req.setAttribute("ave", ave);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("itemid1", itemid);
		req.getRequestDispatcher("/parks_review/reviewlist.jsp").forward(req, resp);

	}
}
