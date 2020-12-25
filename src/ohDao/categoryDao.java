package ohDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.CategoryNameVo;
import semiVo.CategoryVo;

public class categoryDao {

	private static categoryDao instance=new categoryDao();
	private categoryDao() {}
	public static categoryDao getInstance() {
		return instance;
	}
	
	public ArrayList<CategoryNameVo> AllcateName() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CATNAME FROM CATEGORY";
		ArrayList<CategoryNameVo> list = new ArrayList<CategoryNameVo>();
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String catname = rs.getString("catname");
				CategoryNameVo vo = new CategoryNameVo(catname);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<CategoryNameVo> bigCateName(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CATNAME FROM CATEGORY WHERE CATID LIKE '%000'";
		ArrayList<CategoryNameVo> list = new ArrayList<CategoryNameVo>();
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String catname = rs.getString("catname");
				CategoryNameVo vo = new CategoryNameVo(catname);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public int bigCatId(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT CATID FROM CATEGORY WHERE CATNAME =?";
		ArrayList<String> list = new ArrayList<String>();
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int catid = rs.getInt("catid");
				return catid;
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<CategoryNameVo> smallCateName(int bigCateid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String a = String.valueOf(bigCateid);

		String a1 = a.substring(0,5);
		String sql = "SELECT CATNAME FROM CATEGORY WHERE CATID LIKE '"+a1+"%'";
		ArrayList<CategoryNameVo> list = new ArrayList<CategoryNameVo>();
		
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String catname = rs.getString("catname");
				CategoryNameVo vo = new CategoryNameVo(catname);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
