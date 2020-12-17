package yang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBCPBean;
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
}
