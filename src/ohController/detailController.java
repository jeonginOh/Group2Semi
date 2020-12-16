package ohController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ohDao.iteminfoDao;
import semiVo.IteminfoVo;

@WebServlet("/detailitem.do")
public class detailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemid = req.getParameter("itemid");
		iteminfoDao dao = new iteminfoDao();
		IteminfoVo vo = dao.detail(Integer.parseInt(itemid));
		
		Cookie cookie=new Cookie("itemid", itemid);
	    cookie.setMaxAge(60*60*24); //쿠키 시간은 24시간 설정
	    cookie.setPath("/");
	    resp.addCookie(cookie);
		
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
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json.toString());
	}

}
