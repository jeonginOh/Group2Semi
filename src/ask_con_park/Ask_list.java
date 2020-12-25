package ask_con_park;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import askDao.AskDao;
import semiVo.AsktableVo;
@WebServlet("/ask_list")
public class Ask_list extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		HttpSession ss=req.getSession();
		int memid=0;
		String memid1=(String)ss.getAttribute("memid");
	
		if(memid1!=null) {
			memid=Integer.parseInt(memid1);
		}else {
			memid=0;
		}
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		
		AskDao dao=AskDao.getInstance();
		ArrayList<Integer> klist=dao.list_anst();
		ArrayList<Integer> klist2=dao.list_anst2();
	
		ArrayList<AsktableVo> alist=dao.ask_list(startRow, endRow, field, keyword);
		ArrayList<String> userid=dao.select_who();
		int isuser=dao.who_writer(memid);
		System.out.println(isuser);
		int pageCount=(int)Math.ceil(dao.getCount(keyword,field)/5.0);
		int startPageNum=(pageNum-1) /10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum> pageCount) {
			endPageNum=pageCount;
		}
		req.setAttribute("isuser", isuser);
		req.setAttribute("bb", klist2);
		req.setAttribute("aa", klist);
		req.setAttribute("list", alist);
		req.setAttribute("userid", userid);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/jeungIn/main.jsp?spage=/admin_askboard/ask_list.jsp").forward(req, resp);
	}
}
