package com.jeon.admin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeon.Dao.MemberinfoDao;

import org.json.JSONObject;
@WebServlet("/admin/memberlist")
public class MemberList extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        // HashMap<Integer, String> memberstatus = new HashMap<>();
        // memberstatus.put(-1, "탈퇴회원");
        // memberstatus.put(0, "에러");
        // memberstatus.put(1, "정상회원");
        // memberstatus.put(2, "관리자");
        // memberstatus.put(3, "임시회원");
        
        MemberinfoDao dao = MemberinfoDao.getInstance();
        // dao.test("memid", "2");
        //#region parameter
        String order = "asc";
        if (req.getParameter("order")!=null && !req.getParameter("order").isBlank()){
            order = req.getParameter("order");
            System.out.println(order);
        }
        String orderby = "memid";
        if (req.getParameter("orderby")!=null && !req.getParameter("orderby").isBlank()){
            orderby = req.getParameter("orderby");
        }

        String[] searches = new String[8];
        String type = null;
        if (req.getParameter("type")!=null && !req.getParameter("type").isBlank()){
            type = req.getParameter("type");
        }
        String search = null;
        if (req.getParameter("search")!=null && !req.getParameter("search").isBlank()){
            search = req.getParameter("search");
            //age, sex 모두 한 테이블에서 관리하기 때문에 분리해야 한다.
            if (type.equals("age")) {
                search+=".";
            }
            if (type.equals("sex")) {
                type = "age";
                if (search.matches("^(남자|1|3|male)$")) search = ".{6}[1,3]";
                else search = ".{6}[2,4]";
                // if (search.equals("male")) search = ".{6}[1,3]";
                // else search = ".{6}[2,4]";
            }
            if (type.equals("status")) {
                if (search.contains("일반")) search="1";
                else if (search.contains("탈퇴")) search="-1";
                else if (search.contains("관리")) search="2";
                else if (search.contains("임시")) search="3";
            }
            searches[0]=type;
            searches[1]=search;
        }
        // String agemin = null;
        if (req.getParameter("agemin")!=null && !req.getParameter("agemin").isBlank()){
            searches[2]=req.getParameter("agemin");
        }
        // String agemax = null;
        if (req.getParameter("agemax")!=null && !req.getParameter("agemax").isBlank()){
            searches[3]=req.getParameter("agemax");
        }
        // String regdatemin = null;
        if (req.getParameter("regdatemin")!=null && !req.getParameter("regdatemin").isBlank()){
            searches[4]=req.getParameter("regdatemin");
        }
        // String regdatemax = null;
        if (req.getParameter("regdatemax")!=null && !req.getParameter("regdatemax").isBlank()){
            searches[5]=req.getParameter("regdatemax");
        }
        // String pointmin = null;
        if (req.getParameter("pointmin")!=null && !req.getParameter("pointmin").isBlank()){
            searches[6]=req.getParameter("pointmin");
        }
        // String pointmax = null;
        if (req.getParameter("pointmax")!=null && !req.getParameter("pointmax").isBlank()){
            searches[7]=req.getParameter("pointmax");
        }
        // // String statusmin = null;
        // if (req.getParameter("statusmin")!=null && !req.getParameter("statusmin").isBlank()){
        //     searches[8]=req.getParameter("statusmin");
        // }
        // // String statusmax = null;
        // if (req.getParameter("statusmax")!=null && !req.getParameter("statusmax").isBlank()){
        //     searches[9]=req.getParameter("statusmax");
        // }
        boolean empty = true;
        for (String string : searches) {
            if (string!=null && !string.isEmpty()) {
                empty=false;
                break;
            }
        }
        if (empty) searches=null;

        int rowsize = 10;
        if (req.getParameter("rowsize")!=null && !req.getParameter("rowsize").isBlank()) {
            rowsize = Integer.parseInt(req.getParameter("rowsize"));
        }
        int pagenum = 1;
        if (req.getParameter("pagenum")!=null && !req.getParameter("pagenum").isBlank()) {
            pagenum = Integer.parseInt(req.getParameter("pagenum"));
        }
        int pagesize = 10;
        if (req.getParameter("pagesize")!=null && !req.getParameter("pagesize").isBlank()) {
            pagesize = Integer.parseInt(req.getParameter("pagesize"));
        }
        //#endregion
        int size = dao.size(searches);

        int maxpage = size/rowsize;
        if (size%rowsize>0) maxpage++; //반올림

        int endrow = pagenum*rowsize;
        int startrow = endrow-rowsize+1;
        JSONObject json = dao.jsonlist(searches, startrow, endrow, order, orderby);
        if (json==null) json = new JSONObject();
        int startpage = ((pagenum-1)/pagesize)*pagesize+1;
        int endpage = startpage+pagesize-1;
        if (endpage>maxpage) endpage = maxpage;

        System.out.println(
            "pagemaker--------\n"+
            "size: "+size+
            ", maxpage: "+maxpage+
            ", order: "+order+
            ", orderby: "+orderby+
            ", endrow: "+endrow+
            ", startrow: "+startrow+
            ", startpage: "+startpage+
            ", endpage: "+endpage+
            ", pagesize: "+pagesize+
            ", pagenum: "+pagenum
            );

        json.append("maxpage",maxpage);
        json.append("pagenum",pagenum);
        json.append("endrow",endrow);
        json.append("rowsize",size);
        json.append("startrow",startrow);
        json.append("startpage",startpage);
        json.append("endpage",endpage);
        
        resp.getWriter().print(json);
    }
}
