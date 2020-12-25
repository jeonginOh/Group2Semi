package ask_con_park;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import askDao.AskDao;
import itemreviewDao.ItemreviewDao;
import semiVo.AsktableVo;
import semiVo.ItemreviewVo;
@WebServlet("/ask_detail")
public class Ask_detail extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int askid=Integer.parseInt(req.getParameter("askid"));
	HttpSession ss=req.getSession();
	String memid1=(String)ss.getAttribute("memid");
	int memid=0;
	if(memid1!=null) {
		memid=Integer.parseInt(memid1);
	}else {
		memid=0;
	}
	AskDao dao=AskDao.getInstance();
	AsktableVo vo=dao.select_getinfo(askid);
	String username=dao.select_who(askid);
	boolean whos=dao.whowriter(askid, memid);
	req.setAttribute("whos", whos);
	req.setAttribute("list", vo);
	req.setAttribute("username", username);
	System.out.println("디테일확인");
	req.getRequestDispatcher("/jeungIn/main.jsp?spage=/admin_askboard/ask_detail.jsp").forward(req, resp);
	}
	

}
