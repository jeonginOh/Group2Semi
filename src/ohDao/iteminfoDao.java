package ohDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.IteminfoVo;

public class iteminfoDao {
	
	/**
	 * 전체 목록 출력
	 * @return 어레이리스트(아이템인포)
	 */
	public ArrayList<IteminfoVo> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ITEMINFO";
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<IteminfoVo> list = new ArrayList<IteminfoVo>();
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
				
				IteminfoVo vo = 
					new IteminfoVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
	/**
	 * 조건부 검색
	 * @param menu = 검색조건, word = 검색어
	 * @return 어레이리스트(아이템인포)
	 */
	public ArrayList<IteminfoVo> select(String menu,String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ITEMINFO WHERE "+menu+" = ?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,word);
			rs = pstmt.executeQuery();
			ArrayList<IteminfoVo> list = new ArrayList<IteminfoVo>();
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
				
				IteminfoVo vo = 
					new IteminfoVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail);
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
