package admin_park;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import itemreviewDao.ItemreviewDao;
import semiVo.Admin_review_Vo;

@WebServlet("/review_admin1")
public class Admin_park_review extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	ItemreviewDao dao=ItemreviewDao.getInstance();
	int num=1;
	if(req.getParameter("namnam")==null ||Integer.parseInt(req.getParameter("namnam"))==1) {
		num=1;
	}else {
		num=0;
	}
	int num1=1;
	if(req.getParameter("dd")==null ||Integer.parseInt(req.getParameter("dd"))==1) {
		num1=1;
	}else {
		num1=0;
	}
	System.out.println(num1);

	ArrayList<Admin_review_Vo> list=dao.admin_review(num,num1);
	JSONArray jarr=new JSONArray();
	for(Admin_review_Vo vo:list) {
		JSONObject json=new JSONObject();
		json.put("star",vo.getStar());
		json.put("cnt", vo.getCnt());
		json.put("itemname", vo.getItemname());
		jarr.put(json);
	}
	resp.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=resp.getWriter();
	pw.print(jarr.toString());
	}
	
}
