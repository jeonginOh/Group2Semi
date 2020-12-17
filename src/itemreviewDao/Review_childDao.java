package itemreviewDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.Rev_childVo;

public class Review_childDao {
	private static Review_childDao instance=new Review_childDao();
	private Review_childDao() {}
	public static Review_childDao getInstance() {
		return instance;
	}
	public int review_child_insert(Rev_childVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			String sql="insert into rev_child values(rev_child_seq.nextval,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getRevid());
			pstmt.setString(2, vo.getcontext_child());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,null);
		}
		
		
	}
	public ArrayList<Rev_childVo> child_list(int revid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from Rev_child where revid=? order by rchildid desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, revid);
			rs=pstmt.executeQuery();
			ArrayList<Rev_childVo> childlist=new ArrayList<Rev_childVo>();
			while(rs.next()) {
				Rev_childVo vo=new Rev_childVo(
						rs.getInt("rchildid"),
						rs.getInt("revid"),
						rs.getString("context_child"),
						rs.getDate("rchilddate")
						);
				childlist.add(vo);
				
			}
			return childlist;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}
		
	}
	public String child_list_memid(int revid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select id from itemreview NATURAL JOIN iteminfo natural join memberinfo where revid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, revid);
			rs=pstmt.executeQuery();
			rs.next();
			String userid=rs.getNString(1);
			return userid;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}
		
	}
	public int child_delete(int rchildid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=DBCPBean.getConn();
			String sql="delete from rev_child where rchildid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rchildid);
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,null);
		}
	}
}

