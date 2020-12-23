package ask_con_park;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import askDao.AnsDao;

@WebServlet("/ans_delete")
public class Ans_delete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ansid=Integer.parseInt(req.getParameter("ansid"));
		AnsDao dao=AnsDao.getInstance();
		int n=dao.delete_ask(ansid);
		if(n>0) {
			req.setAttribute("code", "success");
			req.getRequestDispatcher("/ask_list").forward(req, resp);
		}else {
			req.setAttribute("code", "fail");
			req.getRequestDispatcher("/ask_list").forward(req, resp);
		}
	}
}
