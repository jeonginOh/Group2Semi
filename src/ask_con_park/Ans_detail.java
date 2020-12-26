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

import askDao.AnsDao;
import askDao.AskDao;
import semiVo.AnstableVo;
import semiVo.AsktableVo;
@WebServlet("/ans_datail")
public class Ans_detail extends HttpServlet{
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 int askid=Integer.parseInt(req.getParameter("askid"));
	HttpSession ss=req.getSession();

	int memid=0;		
	if(ss!=null && ss.getAttribute("memid") !=null && !ss.getAttribute("memid").equals("")) {
		memid=(Integer)ss.getAttribute("memid");
	}else {
		memid=0;
	}
	System.out.println("멤버아이디확인"+memid);
	System.out.println(askid);
	AnsDao dao=AnsDao.getInstance();
	AskDao dao2=AskDao.getInstance();
	ArrayList<AnstableVo> vo=dao.ans_id(askid);
	boolean whos=dao2.whowriter(askid, memid);
	System.out.println("리스트브이오 확인");
	req.setAttribute("list", vo);
	req.setAttribute("whos", whos);
	req.getRequestDispatcher("/jeungIn/main.jsp?spage=/admin_anstable/ans_detail.jsp").forward(req, resp);
	System.out.println("값확인");
}
 
}
