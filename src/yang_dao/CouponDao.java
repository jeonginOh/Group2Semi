package yang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBCPBean;
import semiVo.CouponVo;

public class CouponDao {
	private static CouponDao instance=new CouponDao();
	private CouponDao() {}
	public static CouponDao getInstance() {
		return instance;
	}
	public CouponVo getCoup(int memid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM COUPON WHERE MEMID=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				CouponVo vo=new CouponVo(
						rs.getInt("COUPID"),
						rs.getDate("expire"),
						rs.getInt("memid"),
						rs.getString("context"),
						rs.getInt("avail"));
				return vo;
			}
			return null;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(rs,pstmt,con);
		}
	}
}
