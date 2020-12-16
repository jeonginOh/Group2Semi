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
import yang_dao.IteminfoDao_y;

@WebServlet("/recentShop.yang.do")
public class RecentShoppingList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		JSONArray arr=new JSONArray();
		if(cookies.length==1) { //쿠키가 jsessionid 말고 없는경우
			JSONObject json=new JSONObject();
			json.put("cookie", "null");
			arr.put(json);
		}else {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("JSESSIONID")){ //쿠키이름이 jsessionid인경우 제외
					continue;
				}else{
					int itemid=Integer.parseInt(cookie.getValue());
					IteminfoDao_y dao=IteminfoDao_y.getInstance();
					IteminfoVo vo=dao.detail(itemid);
					JSONObject json=new JSONObject();
					json.put("itemname",vo.getItemname());
					json.put("image",vo.getImage());
					arr.put(json);
				}
			}
		}
		resp.setCharacterEncoding("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
}
