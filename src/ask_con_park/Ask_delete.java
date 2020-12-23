package ask_con_park;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import askDao.AskDao;

@WebServlet("/ask_delete")
public class Ask_delete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int askid=Integer.parseInt(req.getParameter("askid"));
	AskDao dao=AskDao.getInstance();
	int n=dao.delete_ask(askid);
	if(n>0) {
		req.getRequestDispatcher("/ask_list?pageNum=1&field=&keyword=").forward(req, resp);
	}else {
		req.getRequestDispatcher("/ask_list?pageNum=1&field=&keyword=").forward(req, resp);
	}
	}
	
	
}
