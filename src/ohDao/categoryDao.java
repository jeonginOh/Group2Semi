package ohDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.CategoryVo;

public class categoryDao {

	private static categoryDao instance=new categoryDao();
	private categoryDao() {}
	public static categoryDao getInstance() {
		return instance;
	}
	
	public ArrayList<String> cateName() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CATNAME FROM CATEGORY";
		ArrayList<String> list = new ArrayList<String>();
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String catname = rs.getString("catname");
				list.add(catname);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}
	}
}
