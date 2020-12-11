package yang_Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import semiVo.IteminfoVo;
import yang_dao.IteminfoDao;

@WebServlet("/basketlist.do")
public class BasketListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		IteminfoDao dao=IteminfoDao.getInstance();
		ArrayList<IteminfoVo> list=dao.list(id);
		JSONArray arr=new JSONArray();
		for(IteminfoVo vo:list) {
			//System.out.println(vo.getItemname());
			//System.out.println("aaa");
			JSONObject json=new JSONObject();
			json.put("itemname", vo.getItemname());
			json.put("price", vo.getPrice());
			json.put("image", vo.getImage());
			json.put("avail", vo.getAvail());
			json.put("stock", vo.getStock());
			arr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
}
