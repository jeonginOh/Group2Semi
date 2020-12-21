package ohDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBCPBean;

public class storageDao {

	private static storageDao instance=new storageDao();
	private storageDao() {}
	public static storageDao getInstance() {
		return instance;
	}
	
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT NVL(MAX(ITEMID),0) MAXNUM FROM ITEMINFO";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum = rs.getInt(1);
			return maxnum;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public int getCount(String bigCate,String smallCate,String avail,String rowdate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT NVL(COUNT(ITEMID),0) CNT FROM ITEMINFO"+
			" INNER JOIN CATEGORY"+
			" ON ITEMINFO.CATID=CATEGORY.CATID";
			
			if(!bigCate.equals("전체") && smallCate.equals("전체")) {
				sql += "WHERE CATEGORY.CATNAME='"+bigCate+"'";
				if(avail.equals("재고")) {
					sql+=" AND ITEMINFO.AVAIL=1";
				}else if(avail.equals("매진")) {
					sql+=" AND AVAIL=0";
				}
				if()
			}else if(!bigCate.equals("전체") && !smallCate.equals("전체")) {
				sql+="WHERE CATEGORY.CATNAME='"+smallCate+"'";
			}
			
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum = rs.getInt(1);
			return maxnum;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
