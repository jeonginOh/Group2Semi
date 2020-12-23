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
		String spageNum = req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		ArrayList<LogiMemJoinVo> list=dao.list(field,keyword,startRow,endRow);
		int pageCount=(int)Math.ceil(dao.getCount(field, keyword)/10.0);
		int startPageNum=(pageNum-1)/10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		req.setAttribute("list", list);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/yang_admin/logiAdmin.jsp").forward(req, resp);
	}
}
