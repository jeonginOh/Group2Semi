package yang_Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiVo.SalesVo;
import yang_dao.BuyListDao;

@WebServlet("/admsales.do")
public class Admin_SalesController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ymd=req.getParameter("ymd");
		String sdetail=req.getParameter("detail");
		String detail=sdetail.substring(0, sdetail.length()-1); //전(체가 제거) or 연도 or 숫자
		
		BuyListDao bdao=BuyListDao.getInstance();
		ArrayList<SalesVo> sales=bdao.sales(ymd, detail);
		req.setAttribute("sales", sales);
		req.setAttribute("code", "sales");
		req.getRequestDispatcher("/yang_admin/admin_board.jsp").forward(req, resp);
	}
}
