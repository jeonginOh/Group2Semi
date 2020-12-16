package yang_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ohDao.iteminfoDao;
import semiVo.IteminfoVo;

@WebServlet("/buyitems.yang.do")
public class BuyItemsController_y extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemid[]=req.getParameterValues("paramId");
		int totprice=Integer.parseInt(req.getParameter("paramPrice"));
		String amount[]=req.getParameterValues("paramAmount");
		iteminfoDao dao=iteminfoDao.getInstance();
		
		for(int i=0;i<itemid.length;i++) {
			IteminfoVo vo=dao.detail(Integer.parseInt(itemid[i]));
			String itemname=vo.getItemname();
			int price=vo.getPrice();
			int indprice=vo.getPrice()*Integer.parseInt(amount[i]);
		}
	}
}
