package review_con_park;
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

import itemreviewDao.ItemreviewDao;
import semiVo.ItemreviewVo;
@WebServlet("/reviewlist.do")
public class Review_con_list extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		JSONArray arr=new JSONArray();
		ItemreviewDao reviewdao=ItemreviewDao.getInstance();
		ArrayList<ItemreviewVo> reviewlist=reviewdao.review_list(itemid);
		for(ItemreviewVo v:reviewlist) {
			JSONObject json=new JSONObject();
			json.put("title", v.getTitle());			
			arr.put(json);
		}		
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	
	}
}
