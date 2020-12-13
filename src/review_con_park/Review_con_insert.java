package review_con_park;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import itemreviewDao.ItemreviewDao;
import semiVo.ItemreviewVo;
@WebServlet("/reviewinsert.do")
public class Review_con_insert extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//필터 어찌쓸지 몰라서 우선 작성
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		int memid=Integer.parseInt(req.getParameter("memid"));
		String title=req.getParameter("title");
	//	String image=req.getParameter("image");
		String context=req.getParameter("context");
		JSONArray insertarr=new JSONArray();
		ItemreviewVo insertvo=new ItemreviewVo(0,itemid,memid,title,null,context,0,null);
		ItemreviewDao reviewdao=ItemreviewDao.getInstance();
		int n=reviewdao.review_insert(insertvo);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject jsonlist=new JSONObject();
		if(n>0) {
			jsonlist.put("code", "success");
		}else {
			jsonlist.put("code", "fail");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(jsonlist.toString());
	}
}
