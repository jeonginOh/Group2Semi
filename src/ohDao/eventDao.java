package ohDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.storageVo;

public class eventDao {
	
	private static eventDao instance=new eventDao();
	private eventDao() {}
	public static eventDao getInstance() {
		return instance;
	}
	
	public ArrayList<storageVo> bottomevent(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select * from iteminfo inner join category"+
					" on iteminfo.catid=category.catid"+
					" order by stock asc) where rownum<=4";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<storageVo> list = new ArrayList<storageVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				String catname = rs.getString("catname");
				
				storageVo vo = 
						new storageVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail,catname);
					list.add(vo);
			}
			return list;
		}catch(SQLException se){
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<storageVo> topevent(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select * from iteminfo inner join category"+
					" on iteminfo.catid=category.catid"+
					" order by storedate asc) where rownum<=4";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<storageVo> list = new ArrayList<storageVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				String catname = rs.getString("catname");
				
				storageVo vo = 
						new storageVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail,catname);
					list.add(vo);
			}
			return list;
		}catch(SQLException se){
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
