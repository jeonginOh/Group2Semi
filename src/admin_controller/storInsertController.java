package admin_controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin_storageDao.storageDao;
import semiVo.IteminfoVo;

@WebServlet("/storInsert.do")
public class storInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		
		String saveDir = req.getServletContext().getRealPath("/upload");
		MultipartRequest mr = new MultipartRequest(
								req,
								saveDir,
								1024*1024*15,
								"utf-8",
								new DefaultFileRenamePolicy()
												);
		
		String fileName = mr.getFilesystemName("name");
		String itemname = mr.getParameter("itemname");
		String catname = mr.getParameter("catname");
		String price = mr.getParameter("price");
		String factory = mr.getParameter("factory");
		String origin = mr.getParameter("origin");
		String stock = mr.getParameter("stock");
		int expire = Integer.parseInt(mr.getParameter("expire"));
		
		String fileFullPath = saveDir +"/"+fileName;
		
		storageDao dao = storageDao.getInstance();
		int maxnum = dao.getMaxNum();
		int catid = dao.storCatid(catname);
		int in = dao.storageInsert(maxnum+1, itemname, catid, price, factory, origin, stock, expire, fileFullPath);
		resp.setContentType("text/plain;charset=utf-8");
		req.getRequestDispatcher("/admin_jeungin/storageinfo.jsp").forward(req, resp);
	}
}
