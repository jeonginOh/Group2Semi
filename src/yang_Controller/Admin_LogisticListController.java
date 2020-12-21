package yang_Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiVo.LogiMemJoinVo;
import yang_dao.LogisticDao;

@WebServlet("/admLogistic.do")
public class Admin_LogisticListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogisticDao dao=LogisticDao.getInstance();
		ArrayList<LogiMemJoinVo> list=dao.list();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/yang_admin/logiAdmin.jsp").forward(req, resp);
	}
}
