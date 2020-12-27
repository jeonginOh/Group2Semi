package yang_Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import ohDao.iteminfoDao;
import semiVo.BuylistVo;
import semiVo.IteminfoVo;
import semiVo.LogisticVo;
import semiVo.MemberinfoVo;
import yang_dao.BuyListDao;
import yang_dao.LogisticDao;

@WebServlet("/admDetail.do")
public class Admin_DetailLogiController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int memid=Integer.parseInt(req.getParameter("memid"));
		String logiinfo=req.getParameter("logiinfo");
		String addr=req.getParameter("addr");
		LogisticDao logidao=LogisticDao.getInstance();
		MemberinfoDao memdao=MemberinfoDao.getInstance();
		iteminfoDao itemdao=iteminfoDao.getInstance();
		BuyListDao buydao=BuyListDao.getInstance();
		ArrayList<LogisticVo> logivo=logidao.list(memid,logiinfo,addr);
		ArrayList<BuylistVo> buyvo=buydao.list(memid,logiinfo);
		ArrayList<String> itemname=new ArrayList<String>();
		for(LogisticVo vo:logivo) {
			IteminfoVo itemvo=itemdao.detail(vo.getItemid());
			itemname.add(itemvo.getItemname());
		}
		MemberinfoVo memvo=memdao.getVo(memid);
		req.setAttribute("memvo", memvo);
		req.setAttribute("buyvo", buyvo);
		req.setAttribute("logivo", logivo);
		req.setAttribute("itemname",itemname);
		req.getRequestDispatcher("/admin_jeungin/adminFrame.jsp?spage=/yang_admin/admin_logidetail.jsp").forward(req, resp);
	}
}
