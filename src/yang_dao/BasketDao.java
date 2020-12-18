package yang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.BasketVo;

public class BasketDao {
	private static BasketDao instance=new BasketDao();
	private BasketDao() {}
	public static BasketDao getInstance() {
		return instance;
	}
	public int insertDibs(int memid,int itemid,String bd,int amount) { //찜,장바구니 넣기
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		try {
			con=DBCPBean.getConn();
			if(bd.equals("d")) {
				sql="INSERT INTO BASKET VALUES(BAS_SEQ.NEXTVAL,?,?,0)"; //수량이 0개일때 찜
			}else if(bd.equals("b")) {
				sql="INSERT INTO BASKET VALUES(BAS_SEQ.NEXTVAL,?,?,?)"; //수량이 1개일때 장바구니(수량조절할 필요 있음)
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setInt(2, itemid);
			if(bd.equals("b")) {
			pstmt.setInt(3, amount);
			}
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt);
		}
	}
	public int deleteDibs(int memid,int itemid,String bd) { //bd:찜인지 장바구니인지 확인
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		if(bd.equals("d")) {
			sql="DELETE FROM BASKET WHERE MEMID=\r\n" + 
				"? "
				+ "AND ITEMID=? AND COUNT=0"; //찜인상태(수량이0)의 물품을 삭제
		}else if(bd.equals("b")) {
			sql="DELETE FROM BASKET WHERE MEMID=\r\n" + 
					"? "
					+ "AND ITEMID=? AND COUNT>0"; //장바구니(수량이0이상)의 물품을 삭제
		}
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setInt(2, itemid);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt);
		}
	}
	public int buyDelBasket(int memid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="DELETE FROM BASKET WHERE MEMID=? AND COUNT>0";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(pstmt,con);
		}
	}
}
