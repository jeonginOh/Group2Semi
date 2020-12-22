package yang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.BuylistVo;

public class BuyListDao {
	private static BuyListDao instance=new BuyListDao();
	private BuyListDao() {}
	public static BuyListDao getInstance() {
		return instance;
	}
	public int insert(BuylistVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO BUYLIST VALUES(BUYID_SEQ.NEXTVAL,?,?,?,0,SYSDATE,100)";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMemid());
			pstmt.setInt(2, vo.getItemid());
			pstmt.setInt(3, vo.getCount());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt);
		}
	}
	public boolean isBuyItems(int memid, int itemid) { //리뷰쓸때 이 사람이 이 아이템을 샀는지 확인
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM BUYLIST WHERE MEMID=? AND ITEMID=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setInt(2, itemid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}catch(SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public ArrayList<BuylistVo> list(int memid,String logiinfo){//물품상태가 다르면 따로 리스트를 뽑음
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BuylistVo> list=new ArrayList<BuylistVo>();
		String sql="SELECT BUYID,MEMID,ITEMID,COUNT,STATUS,BUYDATE,COUPID "
				+ "FROM BUYLIST NATURAL JOIN LOGISTIC WHERE MEMID=? AND LOGIINFO=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setString(2, logiinfo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BuylistVo vo=new BuylistVo(
						rs.getInt("buyid"),
						rs.getInt("memid"),
						rs.getInt("itemid"),
						rs.getInt("count"),
						rs.getInt("status"),
						rs.getDate("buydate"),
						rs.getInt("coupid"));
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
