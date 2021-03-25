package yang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.BuylistVo;
import semiVo.SalesVo;

public class BuyListDao {
	private static BuyListDao instance=new BuyListDao();
	private BuyListDao() {}
	public static BuyListDao getInstance() {
		return instance;
	}
	public int insert(BuylistVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO BUYLIST VALUES(BUYID_SEQ.NEXTVAL,?,?,?,0,SYSDATE)";
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
	public int insert(ArrayList<BuylistVo> list) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			con.setAutoCommit(false);
			String sql="INSERT INTO BUYLIST VALUES(BUYID_SEQ.NEXTVAL,?,?,?,0,SYSDATE)";
			pstmt=con.prepareStatement(sql);
			for(BuylistVo vo:list) {
				pstmt.setInt(1, vo.getMemid());
				pstmt.setInt(2, vo.getItemid());
				pstmt.setInt(3, vo.getCount());
				pstmt.addBatch();		
				}
			pstmt.executeBatch();
			con.commit();
			return 1;
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
		String sql="SELECT BUYID,MEMID,ITEMID,COUNT,STATUS,BUYDATE "
				+ "FROM BUYLIST NATURAL JOIN LOGISTIC WHERE MEMID=? AND LOGIINFO=? ORDER BY BUYID DESC";
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
						rs.getDate("buydate"));
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
	public ArrayList<BuylistVo>list(String ymd,String dat){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BuylistVo> list=new ArrayList<BuylistVo>();
		String sql="SELECT * FROM(SELECT ITEMID,SUM(COUNT) CNT FROM BUYLIST";
		if(ymd.equals("y") && dat!=null && !dat.equals("")) {
			sql+=" WHERE BUYDATE BETWEEN\r\n" + 
					"to_date('"+dat+"','yyyy') AND add_months(to_date('"+dat+"','yyyy'),13)";
		}else if(ymd.equals("m") && dat!=null && !dat.equals("")) {
			sql+=" WHERE BUYDATE BETWEEN\r\n" + 
					"to_date('"+dat+"','yyyymm') AND add_months(to_date('"+dat+"','yyyymm'),1)";
		}else if(ymd.equals("d") && dat!=null && !dat.equals("")) {

			sql+=" WHERE BUYDATE BETWEEN\r\n" + 
					"to_date('"+dat+"','yyyymmdd') AND to_date('"+dat+"','yyyymmdd')+1";
		}
		sql+=" GROUP BY ITEMID ORDER BY CNT DESC) WHERE ROWNUM<6";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BuylistVo vo=new BuylistVo(
						rs.getInt("itemid"),
						rs.getInt("cnt"));
				list.add(vo);
			}
			return list;
		}catch(SQLDataException sde) {
			System.out.println("잘못된 입력형태입니다.");
			return null;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public ArrayList<SalesVo> sales(String ymd,String detail){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SalesVo> list=new ArrayList<SalesVo>();
		String sql=null;
		if(ymd.equals("y") && detail.equals("전")) {
			sql="SELECT NVL(TO_CHAR(BUYDATE,'yyyy'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST "
					+ "NATURAL JOIN ITEMINFO GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyy'))";
		}else if(ymd.equals("y") && !detail.equals("전")) {
			sql="SELECT NVL(TO_CHAR(BUYDATE,'yyyy'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST "
					+ "NATURAL JOIN ITEMINFO "
					+ "WHERE TO_CHAR(BUYDATE,'YYYY')="+detail+" GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyy'))";
		}else if(ymd.equals("m") && detail.equals("전")) {
			sql="SELECT NVL(TO_CHAR(BUYDATE,'yyyymm'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST "
					+ "NATURAL JOIN ITEMINFO GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyymm'))";
		}else if(ymd.equals("m") && !detail.equals("전")) {
			sql="SELECT NVL(TO_CHAR(BUYDATE,'yyyymm'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST "
					+ "NATURAL JOIN ITEMINFO "
					+ "WHERE TO_CHAR(BUYDATE,'yyyy')="+detail+" GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyymm'))";
		}else if(ymd.equals("d") && detail.equals("전")) {
			sql="SELECT NVL(TO_CHAR(BUYDATE,'yyyymmdd'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST "
					+ "NATURAL JOIN ITEMINFO GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyymmdd'))";
		}else if(ymd.equals("d") && !detail.equals("전")) {
			sql="SELECT NVL(TO_CHAR(BUYDATE,'yyyymmdd'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST "
					+ "NATURAL JOIN ITEMINFO "
					+ "WHERE TO_CHAR(BUYDATE,'mm')="+detail+" GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyymmdd'))";
		}
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SalesVo vo=new SalesVo(
						rs.getString("BDATE"),
						rs.getInt("SPRICE"));
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
	public ArrayList<SalesVo> sales(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SalesVo> list=new ArrayList<SalesVo>();
		String sql="SELECT NVL(ITEMNAME,'합계') INAME,NVL(TO_CHAR(BUYDATE,'yyyymmdd'),'총합') BDATE,SUM(COUNT*PRICE) SPRICE FROM BUYLIST \r\n" + 
				"NATURAL JOIN ITEMINFO GROUP BY ROLLUP(TO_CHAR(BUYDATE,'yyyymmdd'),ITEMNAME)";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SalesVo vo=new SalesVo(
						rs.getString("INAME"),
						rs.getString("BDATE"),
						rs.getInt("SPRICE"));
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
