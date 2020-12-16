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
import yang_dao.BasketDao;
import yang_dao.IteminfoDao_y;

@WebServlet("/basketinsert.do")
public class BasketInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.setAttribute("memid", 23); //session에 id를 넣어보고 테스트(지워야함)
		int memid=(int)session.getAttribute("memid");
		String bd=req.getParameter("bd");
		int itemid=Integer.parseInt(req.getParameter("itemid"));
		String stock=req.getParameter("amount");
		int amount=0;
		if(stock!=null) {
			amount=Integer.parseInt(stock);
		}
		JSONObject json=new JSONObject();
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		//찜,장바구니에 이미 존재하는지 확인하는 작업
		IteminfoDao_y dao2=IteminfoDao_y.getInstance();
		ArrayList<IteminfoVo> list=dao2.list(memid,bd);
		for(IteminfoVo vo:list) {
			if(itemid==vo.getItemid()) {
				json.put("code", "overlap"); //이미 찜이나 장바구니에 있을경우 메시지
				pw.print(json.toString());
				return;
			}
		}
		BasketDao dao=BasketDao.getInstance();
		int n=dao.insertDibs(memid, itemid, bd,amount);
		if(n>0) {
			json.put("code", "success");
		}else {
			json.put("code", "fail");
		}
		pw.print(json.toString());
	}
}
