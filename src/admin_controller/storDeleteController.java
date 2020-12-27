package admin_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin_storageDao.storageDao;

@WebServlet("/stordelete.do")
public class storDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemname = req.getParameter("itemname");
		storageDao dao = storageDao.getInstance();
		dao.delstor(itemname);

		req.getRequestDispatcher("/admin_jeungin/adminFrame.jsp?spage=/admin_jeungin/storageinfo.jsp").forward(req,resp);
	}
}
