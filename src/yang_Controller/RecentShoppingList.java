package yang_Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ohDao.iteminfoDao;
import semiVo.IteminfoVo;

@WebServlet("/recentShop.yang.do")
public class RecentShoppingList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*3+1;
		int endRow=startRow+2;
		int lastpage=(int)Math.ceil((cookies.length-1)/3.0);
		if(endRow>cookies.length-1) {
			endRow=cookies.length-1;
		}
		JSONObject json2=new JSONObject();
		json2.put("lastpage", lastpage);
		JSONArray arr=new JSONArray();
		arr.put(json2);
		for(int i=startRow;i<=endRow;i++) {
				int itemid=Integer.parseInt(cookies[i].getValue());
				iteminfoDao dao=iteminfoDao.getInstance();
				IteminfoVo vo=dao.detail(itemid);
				JSONObject json=new JSONObject();
				json.put("itemid", vo.getItemid());
				json.put("itemname",vo.getItemname());
				json.put("image",vo.getImage());
				json.put("avail", vo.getAvail());
				arr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
}
