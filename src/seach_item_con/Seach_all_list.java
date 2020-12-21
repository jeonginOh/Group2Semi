package seach_item_con;

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

import searchDao.SearchDao;
import semiVo.IteminfoVo;

@WebServlet("/search_list.do")
public class Seach_all_list extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		String search=req.getParameter("search");
		String field=req.getParameter("field");
		String spageNum=req.getParameter("pagenum");
		int pageNum=1;
		if(!spageNum.equals("undefined")) {
			pageNum=Integer.parseInt(spageNum);
		}
		System.out.println("페이지넘버"+spageNum);
		System.out.println(search+","+field+","+pageNum);
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+23;
		SearchDao dao=SearchDao.getinstance();
		ArrayList<IteminfoVo> list=dao.select_list(startRow,endRow,field,search);

		JSONArray jarr=new JSONArray();
		for(IteminfoVo vo : list) {
			JSONObject json=new JSONObject();
			json.put("itemid",vo.getItemid());
			json.put("itemname", vo.getItemname());
			json.put("catid", vo.getCatid());
			json.put("price", vo.getPrice());
			json.put("factory", vo.getFactory());
			json.put("origin", vo.getOrigin());
			json.put("itemimg",vo.getImage());
			json.put("regdate",vo.getStoredate());
			jarr.put(json);
		}
		int pageCount=(int)Math.ceil(dao.getCount(field,search)/10.0);
		int startPageNum=(pageNum-1)/10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		JSONObject paging=new JSONObject();
		paging.put("itemlist", jarr);
		paging.put("pageCount", pageCount);
		paging.put("startPageNum", startPageNum);
		paging.put("endPageNum", endPageNum);
		paging.put("pageNum", pageNum);
		paging.put("field", field);
		paging.put("search", search);
		paging.put("test", "test");
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(paging.toString());
		
		
	}
}
