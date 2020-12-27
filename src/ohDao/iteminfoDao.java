package ohDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.IteminfoVo;
import semiVo.storageVo;

public class iteminfoDao {
	
	private static iteminfoDao instance=new iteminfoDao();
	private iteminfoDao() {}
	public static iteminfoDao getInstance() {
		return instance;
	}
	
	/**
	 * �쟾泥� 紐⑸줉 異쒕젰
	 * @return �뼱�젅�씠由ъ뒪�듃(�븘�씠�뀥�씤�룷)
	 */
	public ArrayList<IteminfoVo> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ITEMINFO";
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<IteminfoVo> list = new ArrayList<IteminfoVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				
				IteminfoVo vo = 
					new IteminfoVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
	/**
	 * 議곌굔遺� 寃��깋
	 * @param menu = 寃��깋議곌굔, word = 寃��깋�뼱
	 * @return �뼱�젅�씠由ъ뒪�듃(�븘�씠�뀥�씤�룷)
	 */
	public ArrayList<storageVo> select(String menu,String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ITEMINFO INNER JOIN CATEGORY ON ITEMINFO.CATID = CATEGORY.CATID WHERE "+menu+" LIKE '%"+word+"%'";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<storageVo> list = new ArrayList<storageVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				String catname = rs.getString("catname");
				
				storageVo vo = 
					new storageVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail,catname);
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
	
	public IteminfoVo detail(int itemid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ITEMINFO WHERE ITEMID=?";
		IteminfoVo vo = null;
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,itemid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String itemname = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				
				vo = new IteminfoVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail);
			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	/**
	 * @param memid= �쉶�썝踰덊샇, bd= basket,dibs(�옣諛붽뎄�땲,李�)援щ텇�옄
	 */
	public ArrayList<IteminfoVo> list(int memid,String bd){ //�쉶�썝蹂� 李�,�옣諛붽뎄�땲�뿉 �떞湲� 臾쇳뭹�뱾 寃��깋
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<IteminfoVo> list=new ArrayList<IteminfoVo>();
		String sql="";
		if(bd.equals("d")) {
			sql="SELECT * FROM ITEMINFO NATURAL JOIN BASKET WHERE MEMID=\r\n" + 
				"? AND COUNT=0 ORDER BY BASID DESC";
		}else if(bd.equals("b")){
			sql="SELECT * FROM ITEMINFO NATURAL JOIN BASKET WHERE MEMID=\r\n" + 
				"? AND COUNT>0 ORDER BY BASID DESC";
		}
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				IteminfoVo vo=new IteminfoVo(
						rs.getInt("itemid"),
						rs.getString("itemname"),
						rs.getInt("catid"),
						rs.getInt("price"),
						rs.getString("factory"),
						rs.getString("origin"),
						rs.getInt("stock"),
						rs.getDate("expire"),
						rs.getDate("storedate"),
						rs.getString("image"),
						rs.getInt("avail"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(rs,pstmt,con);
		}
	}
	//�쉶�썝蹂� 李�,�옣諛붽뎄�땲�뿉 �떞湲� 臾쇳뭹�뱾 寃��깋+�럹�씠吏뺤쿂由�(硫붿씤�럹�씠吏��뿉 3媛쒖뵫 �굹�삤寃� �븯湲�)
	public ArrayList<IteminfoVo> list(int memid,String bd,int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<IteminfoVo> list=new ArrayList<IteminfoVo>();
		String sql="";
		if(bd.equals("d")) {
			sql="SELECT * FROM(\r\n" + 
					"SELECT ROWNUM RNUM, AA.* FROM\r\n" + 
					"(SELECT * FROM ITEMINFO NATURAL JOIN BASKET WHERE MEMID=\r\n" + 
					"? AND COUNT=0 ORDER BY BASID DESC) AA\r\n" + 
					") WHERE RNUM>=? AND RNUM<=?";
		}else if(bd.equals("b")){
			sql="SELECT * FROM(\r\n" + 
					"SELECT ROWNUM RNUM, AA.* FROM\r\n" + 
					"(SELECT * FROM ITEMINFO NATURAL JOIN BASKET WHERE MEMID=\r\n" + 
					"? AND COUNT>0 ORDER BY BASID DESC) AA\r\n" + 
					") WHERE RNUM>=? AND RNUM<=?";
		}
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				IteminfoVo vo=new IteminfoVo(
						rs.getInt("itemid"),
						rs.getString("itemname"),
						rs.getInt("catid"),
						rs.getInt("price"),
						rs.getString("factory"),
						rs.getString("origin"),
						rs.getInt("stock"),
						rs.getDate("expire"),
						rs.getDate("storedate"),
						rs.getString("image"),
						rs.getInt("avail"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(rs,pstmt,con);
		}
	}
	//
	public int getCountBasid(int memid,String bd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		if(bd.equals("d")) {
			sql="SELECT NVL(COUNT(BASID),0) CNT FROM ITEMINFO NATURAL JOIN BASKET WHERE MEMID=\r\n" + 
				"? AND COUNT=0 ORDER BY BASID DESC";
		}else if(bd.equals("b")) {
			sql="SELECT NVL(COUNT(BASID),0) CNT FROM ITEMINFO NATURAL JOIN BASKET WHERE MEMID=\r\n" + 
				"? AND COUNT>0 ORDER BY BASID DESC";
		}
		try{
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			rs=pstmt.executeQuery();
			rs.next();
			int minBasid=rs.getInt("CNT");
			return minBasid;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	
	//移댄뀒怨좊━ 由ъ뒪�듃
	public ArrayList<IteminfoVo> bigcatelist(int startRow,int endRow,int incatid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT BB.*,ROWNUM RNUM FROM (\r\n" + 
				"SELECT * FROM ITEMINFO WHERE CATID LIKE '"+incatid+"%' ORDER BY ITEMID DESC,CATID )BB)\r\n" + 
				"WHERE RNUM>=? AND RNUM<=?";
		try {
			con = DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<IteminfoVo> list = new ArrayList<IteminfoVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				System.out.println(rs.getString("itemname"));
				String itemname = rs.getString("itemname");
				int price = rs.getInt("price");
				int catid = rs.getInt("catid");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				
				IteminfoVo vo = 
					new IteminfoVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public int getMaxItemid() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DBCPBean.getConn();
			String sql = "SELECT NVL(MAX(ITEMID),0) MAXNUM FROM ITEMINFO";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum=rs.getInt(1);
			return maxnum;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public int getItemidCount(String catid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DBCPBean.getConn();
			String sql ="SELECT NVL(COUNT(ITEMID),0) CNT FROM ITEMINFO";
			if(!catid.equals("") && catid!=null ) {
				sql+=" WHERE CATID LIKE '"+catid+"%'";
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
	
	public ArrayList<IteminfoVo> itemList(int startRow,int endRow){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql = "SELECT * FROM "+
					"("+
						"SELECT BB.*,ROWNUM RNUM FROM "+
						"("+
							"SELECT * FROM ITEMINFO "+
							"ORDER BY ITEMID DESC "+
						")"+
					"BB)"+
						"WHERE RNUM>=? AND RNUM<=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<IteminfoVo> list=new ArrayList<IteminfoVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory = rs.getString("factory");
				String origin = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail = rs.getInt("avail");
				
				IteminfoVo vo = 
					new IteminfoVo(itemid,itemname,catid,price,factory,origin,stock,expire,storedate,image,avail);
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
	public int finishBuy(int itemid,int amount) { //구매했을때 재고를 줄이고,재고가 0인것은 판매종료로
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		String sql="UPDATE ITEMINFO SET STOCK=STOCK-"+amount+" WHERE ITEMID=? AND AVAIL=1";
		String sql2="UPDATE ITEMINFO SET AVAIL=0 WHERE STOCK<=0";
		try{
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			pstmt2=con.prepareStatement(sql2);
			int n=pstmt.executeUpdate();
			pstmt2.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,pstmt2);
		}
	}
}

