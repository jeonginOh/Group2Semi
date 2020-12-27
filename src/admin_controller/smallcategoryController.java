package admin_controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ohDao.categoryDao;
import semiVo.CategoryNameVo;

@WebServlet("/smallCateName")
public class smallcategoryController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bigCateName = req.getParameter("bigCateName");
		
		categoryDao dao = categoryDao.getInstance();
		int bigCateid = dao.bigCatId(bigCateName);
		ArrayList<CategoryNameVo> small = dao.smallCateName(bigCateid);
		
		JSONArray arr = new JSONArray();
		for(CategoryNameVo vo:small) {
			JSONObject json = new JSONObject();
			String smallCateName = vo.getCatname();

			json.put("smallCateName", smallCateName);
			
			arr.put(json);
		}
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(arr.toString());
	}
}
