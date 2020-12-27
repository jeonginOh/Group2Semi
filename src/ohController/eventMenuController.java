package ohController;

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

import ohDao.eventDao;
import semiVo.storageVo;

@WebServlet("/eventMenu")
public class eventMenuController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		eventDao dao = eventDao.getInstance();
		ArrayList<storageVo> topevent = dao.topevent();
		ArrayList<storageVo> bottomevent = dao.bottomevent();
		
		JSONObject event = new JSONObject();
		JSONArray toparr = new JSONArray();
		JSONArray bottomarr = new JSONArray();
		
		for(storageVo vo: topevent) {
			JSONObject top = new JSONObject();
			top.put("itemname", vo.getItemname());
			top.put("itemid", vo.getItemid());
			top.put("image", vo.getImage());
			top.put("stock",vo.getStock());
			
			toparr.put(top);
		}
		for(storageVo vo: bottomevent) {
			JSONObject bottom = new JSONObject();
			bottom.put("itemname", vo.getItemname());
			bottom.put("itemid", vo.getItemid());
			bottom.put("image", vo.getImage());
			bottom.put("storedate", vo.getStoredate());
			
			bottomarr.put(bottom);
		}
		event.put("toparr", toparr);
		event.put("bottomarr", bottomarr);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(event.toString());
	}
}
