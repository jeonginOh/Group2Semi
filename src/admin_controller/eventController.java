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

import ohDao.iteminfoDao;
import semiVo.IteminfoVo;

@WebServlet("/eventManage")
public class eventController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String menu = req.getParameter("menu");
		String word = req.getParameter("word");
		iteminfoDao dao = iteminfoDao.getInstance();
		ArrayList<IteminfoVo> list = dao.select(menu, word);
		
		JSONArray arr = new JSONArray();
		for(IteminfoVo vo :list) {
			JSONObject json = new JSONObject();
			json.put("itemid",vo.getItemid());
			json.put("itemname",vo.getItemname());
			json.put("catid",vo.getCatid());
			json.put("price",vo.getPrice());
			json.put("factory",vo.getFactory());
			json.put("origin",vo.getOrigin());
			json.put("stock",vo.getStock());
			json.put("expire",vo.getExpire());
			json.put("storedate",vo.getStoredate());
			json.put("image",vo.getImage());
			json.put("avail",vo.getAvail());
			
			arr.put(json);
		}
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(arr.toString());
		req.getRequestDispatcher("/admin_jeungin/eventManager");
	}
	
	
	
}
