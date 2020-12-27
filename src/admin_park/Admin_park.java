package admin_park;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import itemreviewDao.ItemreviewDao;
import semiVo.Admin_review_Vo;
@WebServlet("/Admin_review")
public class Admin_park extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemname=req.getParameter("itemname");
		ItemreviewDao dao=ItemreviewDao.getInstance();
		int itemid=dao.getItem(itemname);
		System.out.println(itemid);
		System.out.println("////////////////////////////////////");
		resp.sendRedirect("jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?itemid="+itemid);
	}
}