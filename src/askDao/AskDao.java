package askDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.AsktableVo;

public class AskDao {
	private static AskDao instance=new AskDao();
	private AskDao() {}
	public static  AskDao getInstance() {
		return instance;
	}
	public int ask_insert(AsktableVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			String sql="insert into askTable values(ask_seq.nextval,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getAskcat());
			pstmt.setString(2,vo.getTitle());
			pstmt.setString(3, vo.getContext());
			pstmt.setString(4,vo.getImage());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,null);
		}
	}
	public ArrayList<AsktableVo> ask_list(int startRow,int endRow,String field,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			con=DBCPBean.getConn();
			if(field!=null && !field.equals("")) {
			sql="select * from(\r\n" + 
					"select aa.*,rownum rnum from(\r\n" + 
					"(select * from asktable where  "+field+ " like '%" +keyword+ "%' order by askid desc)\r\n" + 
					")aa )where rnum>=? and rnum<=?";

			}else {
				sql="select * from (select aa.*,rownum rnum from( select * from asktable order by askid desc)aa )where rnum>=? and rnum<=?";
				
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<AsktableVo> list=new ArrayList<AsktableVo>();
			while(rs.next()) {
				AsktableVo vo=new AsktableVo(
				rs.getInt("askid"),rs.getInt("askcat"),rs.getString("title"),rs.getString("context"),
				rs.getDate("askdate"),rs.getString("image")
				);
				list.add(vo);
			}return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public int getCount(String keyword,String filed) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		try {
			con = DBCPBean.getConn();
			if(keyword==null || keyword.equals("")) {
			sql = "select NVL(count(askid),0) cnt from asktable";
			}else {
				sql="select NVL(count(askid),0) cnt from asktable where "+filed+" like '%"+keyword+"%'";
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	public AsktableVo select_getinfo(int askid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql="select * from asktable where askid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, askid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				AsktableVo vo=new AsktableVo
				(askid, rs.getInt("askcat"), rs.getString("title"), rs.getString("context"), rs.getDate("askdate"),rs.getString("image"));
			return vo;
			}else {
				return null;
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public int update_ask(AsktableVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql="update asktable set askcat=?,title=?,context=?,image=? where askid=?";
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getAskcat());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContext());
			pstmt.setString(4, vo.getImage());
			pstmt.setInt(5, vo.getAskid());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,null);
		}
	}
	
}
