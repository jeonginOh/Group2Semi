package yang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.LogiMemJoinVo;
import semiVo.LogisticVo;

public class LogisticDao {
	private static LogisticDao instance=new LogisticDao();
	private LogisticDao() {}
	public static LogisticDao getInstance() {
		return instance;
	}
	public int insert(LogisticVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO LOGISTIC VALUES(LOGIID_SEQ.NEXTVAL,?,"
				+ "BUYID_SEQ.CURRVAL,?,?,'물품준비중')";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMemid());
			pstmt.setInt(2, vo.getItemid());
			pstmt.setString(3, vo.getAddr());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt);
		}
	}
	public ArrayList<LogiMemJoinVo> list(String field,String keyword,int startRow, int endRow){ //한 사람이 주문한 여러개 물품들중 대표 한가지만 출력
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LogiMemJoinVo> list= new ArrayList<LogiMemJoinVo>();
		String sql=null;
		if(field!=null && !field.equals("")) {
			sql="SELECT * FROM(\r\n" + 
					"SELECT AA.*,ROWNUM RNUM FROM\r\n" + 
					"(SELECT * FROM LOGISTIC NATURAL JOIN ITEMINFO LEFT JOIN MEMBERINFO ON MEMBERINFO.MEMID=LOGISTIC.MEMID WHERE LOGIID IN(\r\n" + 
					"SELECT NVL(MAX(LOGIID),0) MAXLOGI FROM LOGISTIC GROUP BY MEMID,LOGIINFO,ADDR) "
					+ "AND "+field+" LIKE '%"+keyword+"%' ORDER BY LOGIID DESC\r\n" + 
					") AA\r\n" + 
					")WHERE RNUM>=? AND RNUM<=?";
		}else {
			sql="SELECT * FROM(\r\n" + 
					"SELECT AA.*,ROWNUM RNUM FROM\r\n" + 
					"(SELECT * FROM LOGISTIC NATURAL JOIN ITEMINFO LEFT JOIN MEMBERINFO ON MEMBERINFO.MEMID=LOGISTIC.MEMID WHERE LOGIID IN(\r\n" + 
					"SELECT NVL(MAX(LOGIID),0) MAXLOGI FROM LOGISTIC GROUP BY MEMID,LOGIINFO,ADDR) ORDER BY LOGIID DESC\r\n" + 
					") AA )WHERE RNUM>=? AND RNUM<=?";
		}
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LogiMemJoinVo vo=new LogiMemJoinVo(
						rs.getInt("memid"),
						rs.getString("id"),
						null, null,
						rs.getString("age"),
						rs.getString("email"),
						rs.getString("addr"),
						null,
						rs.getString("phone"),
						rs.getInt("point"),
						rs.getInt("status"),
						rs.getInt("logiid"),
						rs.getInt("buyid"),
						rs.getInt("itemid"),
						rs.getString("logiinfo"),
						rs.getString("itemname"));
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
	public int update(int memid, String elogiinfo, String logiinfo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="UPDATE LOGISTIC SET LOGIINFO=? WHERE MEMID=? AND LOGIINFO=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, logiinfo);
			pstmt.setInt(2, memid);
			pstmt.setString(3, elogiinfo);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt);
		}
	}
	
	public int update(int memid, String elogiinfo, String logiinfo, int itemid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="UPDATE LOGISTIC SET LOGIINFO=? WHERE MEMID=? AND LOGIINFO=? AND ITEMID=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, logiinfo);
			pstmt.setInt(2, memid);
			pstmt.setString(3, elogiinfo);
			pstmt.setInt(4, itemid);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt);
		}
	}
	
	public ArrayList<LogisticVo> list(int memid,String logiinfo,String addr){ //오버로딩
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LogisticVo> list= new ArrayList<LogisticVo>();
		String sql="SELECT * FROM LOGISTIC WHERE MEMID=? AND LOGIINFO=? AND ADDR=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setString(2, logiinfo);
			pstmt.setString(3, addr);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LogisticVo vo=new LogisticVo(
						rs.getInt("logiid"),
						rs.getInt("memid"),
						rs.getInt("buyid"),
						rs.getInt("itemid"),
						rs.getString("addr"),
						rs.getString("logiinfo"));
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
	public ArrayList<LogisticVo> list(int memid,String logiinfo){ //오버로딩
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LogisticVo> list= new ArrayList<LogisticVo>();
		String sql="SELECT * FROM LOGISTIC WHERE MEMID=? AND LOGIINFO=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setString(2, logiinfo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LogisticVo vo=new LogisticVo(
						rs.getInt("logiid"),
						rs.getInt("memid"),
						rs.getInt("buyid"),
						rs.getInt("itemid"),
						rs.getString("addr"),
						rs.getString("logiinfo"));
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
	public ArrayList<LogiMemJoinVo> list(int memid){ //사용자입장 배송조회
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LogiMemJoinVo> list= new ArrayList<LogiMemJoinVo>();
		String sql="SELECT * FROM LOGISTIC NATURAL JOIN MEMBERINFO"
				+ " NATURAL JOIN ITEMINFO WHERE MEMID=? ORDER BY LOGIINFO";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LogiMemJoinVo vo=new LogiMemJoinVo(
						rs.getInt("memid"),
						rs.getString("id"),
						null, null,
						rs.getString("age"),
						rs.getString("email"),
						rs.getString("addr"),
						null,
						rs.getString("phone"),
						rs.getInt("point"),
						rs.getInt("status"),
						rs.getInt("logiid"),
						rs.getInt("buyid"),
						rs.getInt("itemid"),
						rs.getString("logiinfo"),
						rs.getString("itemname"));
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
	public int getCount(String field,String keyword) { //총 목록개수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql ="SELECT NVL(COUNT(LOGIID),0) FROM LOGISTIC NATURAL JOIN ITEMINFO LEFT JOIN MEMBERINFO ON MEMBERINFO.MEMID=LOGISTIC.MEMID \r\n" + 
					"WHERE LOGIID IN(SELECT NVL(MAX(LOGIID),0) MAXLOGI FROM LOGISTIC GROUP BY MEMID,LOGIINFO,ADDR)";
			if(field!=null && !field.equals("")) {
				sql+=" AND "+field+" LIKE '%"+keyword+"%'";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
