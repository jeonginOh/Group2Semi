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

@WebServlet("/bigCateName")
public class bigcategoryController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		categoryDao dao = categoryDao.getInstance();
		ArrayList<CategoryNameVo> big = dao.bigCateList();
		
		JSONArray arr = new JSONArray();
		for(CategoryNameVo vo: big) {
			JSONObject json =new JSONObject();
			String bigCateName=vo.getCatname();
			json.put("bigCateName", bigCateName);
			arr.put(json);
		}
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(arr.toString());
	}
}
