package ohController;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ohDao.categoryDao;

@WebServlet("/cateName")
public class categoryContoroller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		categoryDao dao = categoryDao.getInstance();
		ArrayList<String> list = dao.cateName();
		req.setAttribute("catName", list);
		req.getRequestDispatcher("/jeungIn/categoryBanner.jsp").forward(req,resp);
	}
}
