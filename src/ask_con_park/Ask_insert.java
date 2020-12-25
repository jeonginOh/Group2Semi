package ask_con_park;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import askDao.AskDao;
import semiVo.AsktableVo;
@WebServlet("/ask_insert")
public class Ask_insert extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getSession().getServletContext().getRealPath("/fileFolder");
		int size=1024 * 1024 * 10;
		int field1=0;
		HttpSession se=req.getSession();
		int memid=Integer.parseInt((String)se.getAttribute("memid"));
		try {
			MultipartRequest mr=new MultipartRequest(req,path,size,"utf-8",new DefaultFileRenamePolicy());
			String askcat=mr.getParameter("askcat");
			
			if(askcat.equals("item")) {
				field1=1;
			}else if(askcat.equals("basong")) {
				field1=2;
			}else if(askcat.equals("hwanbull")) {
				field1=3;
			}else if(askcat.equals("etc")) {
				field1=4;
			}
			String title=mr.getParameter("title");
			String context=mr.getParameter("context");
			String file=mr.getFilesystemName("image");
			AsktableVo avo=new AsktableVo(0,memid,field1,title,context,null,file);
			AskDao askdao=AskDao.getInstance();
			int n=askdao.ask_insert(avo);
			resp.setContentType("text/plain;charset=utf-8");
			if(n>0) {
				req.setAttribute("code", "success");
				req.getRequestDispatcher("/jeungIn/main.jsp?spage=/ask_list?pageNum=1&field=&keyword=").forward(req, resp);
			}else {
				req.setAttribute("code", "fail");
				req.getRequestDispatcher("/jeungIn/main.jsp?spage=/ask_list?pageNum=1&field=&keyword=").forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
