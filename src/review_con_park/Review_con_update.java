package review_con_park;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import itemreviewDao.ItemreviewDao;
import semiVo.ItemreviewVo;

@WebServlet("/reviewupdate")
public class Review_con_update extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int revid=Integer.parseInt(req.getParameter("revid"));
	int itemid=Integer.parseInt(req.getParameter("itemid"));
	ItemreviewDao dao=ItemreviewDao.getInstance();
	ItemreviewVo vo=dao.review_listupdate(revid);
	req.setAttribute("vo", vo);
	
	req.getRequestDispatcher("/parks_review/reviewupdateform.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	String path = req.getSession().getServletContext().getRealPath("/fileFolder");
	int size = 1024 * 1024 * 10;
	try {
		MultipartRequest multi = new MultipartRequest(req, path, size, "utf-8", new DefaultFileRenamePolicy());
		int revid=Integer.parseInt(multi.getParameter("revid"));
		int itemid=Integer.parseInt(multi.getParameter("itemid"));
		int star=Integer.parseInt(multi.getParameter("star"));
		String title = multi.getParameter("title");
		String context = multi.getParameter("context");
		String file = multi.getOriginalFileName("image");
		String img=multi.getContentType("img2");
		System.out.println(img);
	
		
		System.out.println(multi.getOriginalFileName(file));
		if(file==null) {
			file=multi.getOriginalFileName("img1");
			
		}
		System.out.println(multi.getOriginalFileName("img1"));
		File f = multi.getFile("file1");
		ItemreviewVo insertvo = new ItemreviewVo(revid, 0, 0, title, file, context, star, null);
		ItemreviewDao reviewdao = ItemreviewDao.getInstance();
		int n = reviewdao.review_update(insertvo);
		resp.setContentType("text/plain;charset=utf-8");
		if (n > 0) {
			System.out.println("실행확인");
			req.setAttribute("code", "success");
			req.setAttribute("itemid", itemid);
			req.getRequestDispatcher("/parks_review/insertsucc.jsp").forward(req, resp);
		} else {
			System.out.println("실행확인2");
			req.setAttribute("code", "fail");
			req.setAttribute("itemid", itemid);
			req.getRequestDispatcher("/parks_review/insertsucc.jsp").forward(req, resp);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
}
