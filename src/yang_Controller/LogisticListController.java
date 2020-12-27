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
import semiVo.BuylistVo;
import semiVo.IteminfoVo;
import semiVo.LogiMemJoinVo;
import semiVo.LogisticVo;
import semiVo.MemberinfoVo;
import yang_dao.BuyListDao;
import yang_dao.LogisticDao;

@WebServlet("/logistic.do")
public class LogisticListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss=req.getSession();
		int memid=0;		
		if(ss!=null && ss.getAttribute("memid") !=null && !ss.getAttribute("memid").equals("")) {
			memid=(Integer)ss.getAttribute("memid");
		}else {
			memid=0;
		}
		
		LogisticDao logidao=LogisticDao.getInstance();
		iteminfoDao itemdao=iteminfoDao.getInstance();
		BuyListDao buydao=BuyListDao.getInstance();
		ArrayList<LogisticVo> logivo1=logidao.list(memid, "물품준비중");
		ArrayList<LogisticVo> logivo2=logidao.list(memid,"발송완료");
		ArrayList<LogisticVo> logivo3=logidao.list(memid,"배송완료");
		ArrayList<LogisticVo> logivo4=logidao.list(memid,"반품처리");
		ArrayList<BuylistVo> buyvo1=buydao.list(memid,"물품준비중");
		ArrayList<BuylistVo> buyvo2=buydao.list(memid,"발송완료");
		ArrayList<BuylistVo> buyvo3=buydao.list(memid,"배송완료");
		ArrayList<BuylistVo> buyvo4=buydao.list(memid,"반품처리");
		ArrayList<String> itemname1=new ArrayList<String>();
		ArrayList<String> itemname2=new ArrayList<String>();
		ArrayList<String> itemname3=new ArrayList<String>();
		ArrayList<String> itemname4=new ArrayList<String>();
		for(LogisticVo vo:logivo1) {
			IteminfoVo itemvo=itemdao.detail(vo.getItemid());
			itemname1.add(itemvo.getItemname());
		}
		for(LogisticVo vo:logivo2) {
			IteminfoVo itemvo=itemdao.detail(vo.getItemid());
			itemname2.add(itemvo.getItemname());
		}
		for(LogisticVo vo:logivo3) {
			IteminfoVo itemvo=itemdao.detail(vo.getItemid());
			itemname3.add(itemvo.getItemname());
		}
		for(LogisticVo vo:logivo4) {
			IteminfoVo itemvo=itemdao.detail(vo.getItemid());
			itemname4.add(itemvo.getItemname());
		}
		req.setAttribute("buyvo1", buyvo1);
		req.setAttribute("buyvo2", buyvo2);
		req.setAttribute("buyvo3", buyvo3);
		req.setAttribute("buyvo4", buyvo4);
		req.setAttribute("logivo1", logivo1);
		req.setAttribute("logivo2", logivo2);
		req.setAttribute("logivo3", logivo3);
		req.setAttribute("logivo4", logivo4);
		req.setAttribute("itemname1",itemname1);
		req.setAttribute("itemname2",itemname2);
		req.setAttribute("itemname3",itemname3);
		req.setAttribute("itemname4",itemname4);
		req.getRequestDispatcher("/jeungIn/main.jsp?spage=/yang/checkShip.jsp").forward(req, resp);
	}
}
