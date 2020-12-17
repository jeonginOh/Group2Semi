package yang_Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ohDao.iteminfoDao;
import semiVo.IteminfoVo;

@WebServlet("/buyitems.yang.do")
public class BuyItemsController_y extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemid[]=req.getParameterValues("paramId");
		int totprice=Integer.parseInt(req.getParameter("paramPrice"));
		String amount[]=req.getParameterValues("paramAmount");
		HttpSession session=req.getSession();
		int memid=(int)session.getAttribute("memid");
		if(session.getAttribute("memid")==null) {
			
		}
		iteminfoDao dao=iteminfoDao.getInstance();
		ArrayList<IteminfoVo> list=new ArrayList<IteminfoVo>();
		ArrayList<String> amountlist=new ArrayList<String>();
		for(int i=0;i<itemid.length;i++) {
			IteminfoVo vo=dao.detail(Integer.parseInt(itemid[i]));
			amountlist.add(amount[i]);
			list.add(vo);
		}
		
		req.setAttribute("memid", memid);
		req.setAttribute("list", list);
		req.setAttribute("totprice", totprice);
		req.setAttribute("amountlist", amountlist);
		req.getRequestDispatcher("/jeungIn/main.jsp?spage=/yang/buyPage_y.jsp").forward(req, resp);
	}
}
