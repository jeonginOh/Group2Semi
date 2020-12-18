package yang_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiVo.BuylistVo;
import semiVo.LogisticVo;
import yang_dao.BasketDao;
import yang_dao.BuyListDao;
import yang_dao.LogisticDao;

@WebServlet("/buyItemsSave.yang.do")
public class BuyItemsSaveController extends HttpServlet{ //구매한목록,배송정보테이블에 추가
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String item[]=req.getParameterValues("item");
		String amount[]=req.getParameterValues("amount");
		int memid=Integer.parseInt(req.getParameter("memid"));
		String addr=req.getParameter("addr");
		BuyListDao dao=BuyListDao.getInstance();
		LogisticDao logidao=LogisticDao.getInstance();
		int n=0;
		int n2=0;
		for(int i=0;i<item.length;i++) {
			BuylistVo vo=new BuylistVo(0,
					memid,
					Integer.parseInt(item[i]),
					Integer.parseInt(amount[i]),
					0,null,0);
			LogisticVo logivo=new LogisticVo(0, memid, 0,
					Integer.parseInt(item[i]),
					addr,null);
			n=dao.insert(vo);
			if(n>0) {
				n2=logidao.insert(logivo);
				if(n2<=0) {
					n=0; n2=0;
					break;
				}
			}else {
				n=0;
				n2=0;
				break;
			}
		}
		if(n>0 && n2>0) { //성공했으면 장바구니에서도 제거
			BasketDao bdao=BasketDao.getInstance();
			bdao.buyDelBasket(memid);
			//resp.sendRedirect(req.getContextPath()+"/jeungIn/main.jsp"); //영수증페이지로
			req.setAttribute("code", "오류로 인해 결제실패. 관리자에게 문의해주세요.");
			req.getRequestDispatcher(req.getContextPath()+"/jeungIn/main.jsp?spage=buyPage_y.jsp").forward(req, resp);
		}else {
			req.setAttribute("code", "오류로 인해 결제실패. 관리자에게 문의해주세요.");
			req.getRequestDispatcher(req.getContextPath()+"/jeungIn/main.jsp?spage=buyPage_y.jsp").forward(req, resp);
		}
	}
}
