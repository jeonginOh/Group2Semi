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

public class Review_con_main extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		ItemreviewDao dao=ItemreviewDao.getInstance();
		ArrayList<ItemreviewVo> vo=dao.review_list(itemid);

	}
}
