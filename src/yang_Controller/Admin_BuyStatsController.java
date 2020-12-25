package yang_Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ohDao.iteminfoDao;
import semiVo.BuylistVo;
import semiVo.IteminfoVo;
import yang_dao.BuyListDao;

@WebServlet("/admbuystats.do")
public class Admin_BuyStatsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ymd=req.getParameter("ymd");
		String dat=req.getParameter("dat");
		BuyListDao bdao=BuyListDao.getInstance();
		iteminfoDao idao=iteminfoDao.getInstance();
		ArrayList<BuylistVo> list=bdao.list(ymd, dat);
		ArrayList<String> itemname=new ArrayList<String>();
		if(list!=null) {
			for(BuylistVo vo:list) {
				IteminfoVo ivo=idao.detail(vo.getItemid());
				itemname.add(ivo.getItemname());
			}
		}
		req.setAttribute("list", list);
		req.setAttribute("itemname",itemname);
		req.setAttribute("code", "rank");
		req.getRequestDispatcher("/yang_admin/admin_board.jsp").forward(req, resp);
	}
}
