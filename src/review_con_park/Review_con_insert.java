package review_con_park;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import itemreviewDao.ItemreviewDao;
import semiVo.ItemreviewVo;

@WebServlet("/reviewinsert.do")
public class Review_con_insert extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getSession().getServletContext().getRealPath("/fileFolder");
		// 파일저장경로
		int size = 1024 * 1024 * 10;
		// 파일크기
		// 업로드한 파일이름(이름이 변경될수 있다)
		try {
			MultipartRequest multi = new MultipartRequest(req, path, size, "utf-8", new DefaultFileRenamePolicy());
			int itemid = Integer.parseInt(multi.getParameter("itemid"));
			int memid = Integer.parseInt(multi.getParameter("memid"));
			int star=Integer.parseInt(multi.getParameter("star"));
			System.out.println(star);
			System.out.println("dddd");
			String title = multi.getParameter("title");
			String context = multi.getParameter("context");
			String file = multi.getOriginalFileName("image");
			File f = multi.getFile("file1");
			System.out.println(file);
			JSONArray insertarr = new JSONArray();
			ItemreviewVo insertvo = new ItemreviewVo(0, itemid, memid, title, file, context, star, null);
			ItemreviewDao reviewdao = ItemreviewDao.getInstance();
			int n = reviewdao.review_insert(insertvo);
			resp.setContentType("text/plain;charset=utf-8");
			JSONObject jsonlist = new JSONObject();
			if (n > 0) {
				req.setAttribute("code", "success");
				req.setAttribute("itemid", itemid);
				req.getRequestDispatcher("/parks_review/insertsucc.jsp").forward(req, resp);
			} else {
				req.setAttribute("code", "fail");
				req.setAttribute("itemid", itemid);
				req.getRequestDispatcher("/parks_review/insertsucc.jsp").forward(req, resp);
			}
			PrintWriter pw = resp.getWriter();
			pw.print(jsonlist.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
