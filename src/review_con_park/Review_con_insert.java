package review_con_park;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiVo.ItemreviewVo;

public class Review_con_insert extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//필터 어찌쓸지 몰라서 우선 작성
	
		String title=req.getParameter("title");
		String image=req.getParameter("image");
		String context=req.getParameter("context");
		ItemreviewVo vo=new ItemreviewVo(0,0,0,title,image,context,0,null);
		
	}
}
