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

import ohDao.iteminfoDao;
import semiVo.IteminfoVo;

@WebServlet("/list.do")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String spageNum = req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		
		iteminfoDao dao = iteminfoDao.getInstance();
		ArrayList<IteminfoVo> list = dao.itemList(startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getItemidCount()/10.0);
		int startPageNum=(pageNum-1)/10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		JSONObject pageJson = new JSONObject();
		pageJson.put("startPageNum", startPageNum);
		pageJson.put("endPageNum", endPageNum);
		
		JSONArray arr = new JSONArray(); //JSONObeject를 하나 더 만들어서 여기에 startpagenum ,endpagenum,
		for(IteminfoVo vo:list) {
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
		pageJson.put("arr", arr);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(pageJson.toString());
	}
}
