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
	public int insertDibs(String id,int itemid) { //찜하기 기능
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO BASKET VALUES(BAS_SEQ.NEXTVAL,(\r\n" + 
				"SELECT MEMID FROM MEMBERINFO WHERE ID=?),?,0)"; //수량이 0개일때 찜
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	public int deleteDibs(String id,int itemid) { //찜 삭제기능
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="DELETE FROM BASKET WHERE MEMID=\r\n" + 
				"(SELECT MEMID FROM MEMBERINFO WHERE ID=?) "
				+ "AND ITEMID=? AND COUNT=0"; //찜인상태(수량이0)의 물품을 삭제
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
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
}
