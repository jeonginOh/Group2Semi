package searchDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.IteminfoVo;
import semiVo.ItemreviewVo;

public class SearchDao {
	private static SearchDao instance=new SearchDao();
	private SearchDao() {}
	public static SearchDao getinstance() {
		return instance;
	}
	public ArrayList<IteminfoVo> select_list(int startRow,int endRow,String field,String search){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			
			con=DBCPBean.getConn();
			sql="select * from(  \r\n" + 	
					"					select aa.*,rownum rnum from \r\n" + 
					"					(select * from iteminfo where "+field+" like '%"+search+"%' order by itemid desc)\r\n" + 
					"					  aa   \r\n" + 
					"					)where rnum>=? and rnum<=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<IteminfoVo> list=new ArrayList<IteminfoVo>();
			while(rs.next()) {
				IteminfoVo vo=new IteminfoVo(rs.getInt("itemid"),rs.getString("itemname"),rs.getInt("catid"),rs.getInt("price"),
						rs.getString("factory"),rs.getString("ORIGIN"),rs.getInt("stock"),rs.getDate("expire"),rs.getDate("storedate")
						,rs.getString("image"),rs.getInt("avail"));
				list.add(vo);
			}return list;
			
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public int getCount(String field,String search) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select NVL(count(itemid),0) cnt from iteminfo";
			if(field!=null && !field.equals("")) {
				sql += " where " + field + " like '%"+ search + "%'";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt(1);
			return cnt;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public ArrayList<IteminfoVo> alllist2(int startRow,int endRow,String field,String search){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			if(field!=null && search!=null) {
			con=DBCPBean.getConn();
			sql="select * from(  \r\n" + 
					"					select aa.*,rownum rnum from \r\n" + 
					"					(select * from iteminfo where "+field+" like '%"+search+"%' order by itemid desc)\r\n" + 
					"					  aa   \r\n" + 
					"					)where rnum>=? and rnum<=?";
			}else {
				sql="select * from  \r\n" + 
						"					(	  select aa.*,rownum rnum from  \r\n" + 
						"					(	select * from iteminfo order by itemid desc) aa  \r\n" + 
						"						) where rnum>=? and rnum<=?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<IteminfoVo> list=new ArrayList<IteminfoVo>();
			while(rs.next()) {
				IteminfoVo vo=new IteminfoVo(rs.getInt("itemid"),rs.getString("itemname"),rs.getInt("catid"),rs.getInt("price"),
						rs.getString("factory"),rs.getString("ORIGIN"),rs.getInt("stock"),rs.getDate("expire"),rs.getDate("storedate")
						,rs.getString("image"),rs.getInt("avail"));
				list.add(vo);
			}return list;
			
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
}
