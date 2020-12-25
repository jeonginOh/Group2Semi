package ask_con_park;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import askDao.AnsDao;
import askDao.AskDao;
import semiVo.AnstableVo;
import semiVo.AsktableVo;
@WebServlet("/ans_insert")
public class Ans_insert extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int askid=Integer.parseInt(req.getParameter("askid"));
	AskDao dao=AskDao.getInstance();
	AsktableVo vo=dao.select_getinfo(askid);
	String username=dao.select_who(askid);
	req.setAttribute("list", vo);
	req.setAttribute("username", username);
	req.getRequestDispatcher("/jeungIn/main.jsp?spage=/admin_anstable/admin_insert.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String path = req.getSession().getServletContext().getRealPath("/fileFolder");
		int size = 1024 * 1024 * 10;
		int askcat1=0;
		try {
			MultipartRequest mr=new MultipartRequest(req, path,size,"utf-8",new DefaultFileRenamePolicy());
			String askcat=mr.getParameter("askcat");
			if(askcat.equals("item")) {
				askcat1=1;
			}else if(askcat.equals("basong")) {
				askcat1=2;
			}else if(askcat.equals("hwanbull")) {
				askcat1=3;
			}else if(askcat.equals("etc")) {
				askcat1=4;
			}
			int askid=Integer.parseInt(mr.getParameter("askid"));
			String title=mr.getParameter("title");
			String context=mr.getParameter("context");
			String file=mr.getOriginalFileName("image");
			String file1=mr.getParameter("image1");
			if(file==null) {
				file=file1;
			}
			AnstableVo vo=new AnstableVo(0, askid, context, file, null);
			AnsDao dao=AnsDao.getInstance();
			int n=dao.ans_insert(vo);
			resp.setContentType("text/plain;charset=utf-8");
			if(n>0) {
				req.setAttribute("code", "good");
				req.getRequestDispatcher("/jeungIn/main.jsp?spage=/ask_list?pageNum=1&field=&keyword=").forward(req, resp);
			}else {
				req.setAttribute("code", "bad");
				req.getRequestDispatcher("/jeungIn/main.jsp?spage=/ask_list?pageNum=1&field=&keyword=").forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
