package askDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.Authenticator.Result;

import db.DBCPBean;
import semiVo.AnstableVo;

public class AnsDao {
	private static AnsDao instance=new AnsDao();
	private AnsDao() {}
	public static  AnsDao getInstance() {
		return instance;
	}
	public int ans_insert(AnstableVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into anstable values(ans_seq.nextval,?,?,?,sysdate)";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getAskid());
			pstmt.setString(2, vo.getContext());
			pstmt.setString(3, vo.getImage());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,null);
		}
	}

	/*
	 * public ArrayList<AnstableVo> ans_list(int askid){ Connection con=null;
	 * PreparedStatement pstmt=null; ResultSet rs=null; String
	 * sql="select * from anstable where ankid=?"; try { con=DBCPBean.getConn();
	 * pstmt=con.prepareStatement(sql); pstmt.setInt(1, askid);
	 * rs=pstmt.executeQuery(); ArrayList<AnstableVo> list=new
	 * ArrayList<AnstableVo>(); if(rs.next()) { AnstableVo vo=new
	 * AnstableVo(rs.getInt("ansid"),rs.getInt("askid"),rs.getString("context"),rs.
	 * getString("image"),rs.getDate("ansdate")); list.add(vo); }return list;
	 * }catch(SQLException se) { se.printStackTrace(); return null; }finally {
	 * DBCPBean.close(con,pstmt,rs); } }
	 */
	public ArrayList<AnstableVo> ans_id(int askid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from anstable inner join asktable on anstable.askid=asktable.askid where anstable.askid=? order by anstable.askid desc";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, askid);
			rs=pstmt.executeQuery();
			ArrayList<AnstableVo> list=new ArrayList<AnstableVo>();
			while(rs.next()) {
				AnstableVo vo=new AnstableVo(rs.getInt("ansid"),askid,rs.getString("context"),rs.getString("image"),rs.getDate("ansdate"));
				list.add(vo);
			}return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public int delete_ask(int ansid) {
		Connection con = null;
		
		PreparedStatement pstmt2=null;
		try {
			String sql2="delete from anstable where ansid=?";
			con=DBCPBean.getConn();
			pstmt2=con.prepareStatement(sql2);
			
			pstmt2.setInt(1, ansid);
			
			return pstmt2.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt2,null);
		}
	}
}
