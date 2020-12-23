package admin_controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin_storageDao.storageDao;
import ohDao.iteminfoDao;
import semiVo.IteminfoVo;
import semiVo.storageVo;

@WebServlet("/storList.do")
public class storListAllController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spageNum=req.getParameter("pageNum");
		String bigCate = req.getParameter("bigCate");
		String smallCate = req.getParameter("smallCate");
		String avail = req.getParameter("avail");
		String itemname = req.getParameter("itemname");
		String factory = req.getParameter("factory");
		String origin = req.getParameter("origin");
		String stDate = req.getParameter("stDate");
		String endDate = req.getParameter("endDate");
		
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		
		storageDao dao = storageDao.getInstance();
		ArrayList<storageVo> list = dao.storageList(startRow,endRow,bigCate,smallCate,avail,itemname,factory,origin,stDate,endDate);
		int pageCount=(int)Math.ceil(dao.getCount(bigCate,smallCate,avail,itemname,factory,origin,stDate,endDate)/10.0);
		int startPageNum=(pageNum-1)/10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("bigCate", bigCate);
		req.setAttribute("smallCate", smallCate);
		req.setAttribute("avail", avail);
		req.setAttribute("itemname", itemname);
		req.setAttribute("factory", factory);
		req.setAttribute("origin", origin);
		req.setAttribute("stDate", stDate);
		req.setAttribute("endDate", endDate);
		
		req.getRequestDispatcher("/admin_jeungin/storageinfo.jsp").forward(req,resp);
	}
}
