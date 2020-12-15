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
import yang_dao.IteminfoDao_y;

@WebServlet("/mainlist.yang")
public class MainPageListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String bd=req.getParameter("bd");
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*3+1;
		int endRow=startRow+2;
		IteminfoDao_y dao=IteminfoDao_y.getInstance();
		int lastpage=(int)Math.ceil(dao.getCountBasid(id)/3.0);
		JSONObject json2=new JSONObject();
		json2.put("lastpage", lastpage);
		ArrayList<IteminfoVo> list=dao.list(id, bd, startRow, endRow);
		JSONArray arr=new JSONArray();
		arr.put(json2); //마지막페이지를 배열의 제일 첫번째에 넣기
		for(IteminfoVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("itemid", vo.getItemid());
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
