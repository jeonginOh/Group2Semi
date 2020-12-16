package yang_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buyitems.yang.do")
public class BuyItemsController_y extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemid[]=req.getParameterValues("paramId");
		int totprice=Integer.parseInt(req.getParameter("paramPrice"));
		String amount[]=req.getParameterValues("paramAmount");
		
		for(int i=0;i<itemid.length;i++) {
			
		}
	}
}
