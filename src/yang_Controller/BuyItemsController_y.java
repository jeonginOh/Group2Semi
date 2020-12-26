package yang_Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeon.Dao.MemberinfoDao;

import ohDao.iteminfoDao;
import semiVo.CouponVo;
import semiVo.IteminfoVo;
import semiVo.MemberinfoVo;
import yang_dao.CouponDao;

@WebServlet("/buyitems.yang.do")
public class BuyItemsController_y extends HttpServlet{ //정보들 가져와서 구매페이지로 넘기는 컨트롤러
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemid[]=req.getParameterValues("paramId");
		int totprice=Integer.parseInt(req.getParameter("paramPrice"));
		String amount[]=req.getParameterValues("paramAmount");
		HttpSession session=req.getSession();
		int memid=(int)session.getAttribute("memid");
		if(session.getAttribute("tempuser")==null) {
			MemberinfoDao memdao=MemberinfoDao.getInstance(); //회원정보 가져오기
			MemberinfoVo mem=memdao.getVo(memid);
			iteminfoDao dao=iteminfoDao.getInstance(); //물품정보 가져오기
			CouponDao coupdao=CouponDao.getInstance();
			CouponVo coup=coupdao.getCoup(memid);
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
			req.setAttribute("mem", mem);
			req.setAttribute("coup", coup);
			req.getRequestDispatcher("/jeungIn/main.jsp?spage=/yang/buyPage_y.jsp").forward(req, resp);
		}else if((boolean)session.getAttribute("tempuser")) {
			resp.sendRedirect(req.getContextPath()+"/jeungIn/main.jsp?spage=/yang/editinfo.jsp");
		}
	}
}
