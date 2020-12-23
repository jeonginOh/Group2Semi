package admin_storageDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.IteminfoVo;
import semiVo.storageVo;

public class storageDao {

	private static storageDao instance=new storageDao();
	private storageDao() {}
	public static storageDao getInstance() {
		return instance;
	}
	
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT NVL(MAX(ITEMID),0) MAXNUM FROM ITEMINFO";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum = rs.getInt(1);
			return maxnum;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public int getCount(String bigCate,String smallCate,String avail,String itemname,String factory,String origin,String stDate,String endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT NVL(COUNT(ITEMID),0) CNT FROM ITEMINFO"+
			" INNER JOIN CATEGORY"+
			" ON ITEMINFO.CATID=CATEGORY.CATID";
			
			if(!bigCate.equals("전체") && smallCate.equals("전체")) {
				sql += " WHERE CATEGORY.CATNAME='"+bigCate+"'";
				
				if(avail.equals("재고")) {
					sql+=" AND ITEMINFO.AVAIL=1";
				}else if(avail.equals("매진")) {
					sql+=" AND ITEMINFO.AVAIL=0";}else {}
				
				if(!itemname.equals("") && itemname!=null) {
					sql+=" AND ITEMINFO.ITEMNAME="+itemname;
				}else {}
				
				if(!factory.equals("") && factory!=null) {
					sql+=" AND ITEMINFO.FACTORY="+factory;
				}else {}
				
				if(!origin.equals("") && origin!=null) {
					sql+=" AND ITEMINFO.ORIGIN="+origin;
				}else {}
				
				if(!stDate.equals("") && stDate!=null && !endDate.equals("") && endDate!=null) {
					sql+=" AND ITEMINFO.STOREDATE BETWEEN "+stDate+" AND "+endDate;
				}else {}
				
				
			}else if(!bigCate.equals("전체") && !smallCate.equals("전체")) {
				sql+=" WHERE CATEGORY.CATNAME='"+smallCate+"'";
				
				if(avail.equals("재고")) {
					sql+=" AND ITEMINFO.AVAIL=1";
				}else if(avail.equals("매진")) {
					sql+=" AND ITEMINFO.AVAIL=0";}else {}
				
				if(!itemname.equals("") && itemname!=null) {
					sql+=" AND ITEMINFO.ITEMNAME="+itemname;
				}else {}
				
				if(!factory.equals("") && factory!=null) {
					sql+=" AND ITEMINFO.FACTORY="+factory;
				}else {}
				
				if(!origin.equals("") && origin!=null) {
					sql+=" AND ITEMINFO.ORIGIN="+origin;
				}else {}
				
				if(!stDate.equals("") && stDate!=null && !endDate.equals("") && endDate!=null) {
					sql+=" AND BETWEEN "+stDate+" AND "+endDate;
				}else {}
				
			}else if(bigCate.equals("전체")) {
				if(avail.equals("재고")) {
					sql+=" WHERE ITEMINFO.AVAIL=1";
				}else if(avail.equals("매진")) {
					sql+=" WHERE ITEMINFO.AVAIL=0";}else {}
				
				if(!itemname.equals("") && itemname!=null) {
					sql+=" AND ITEMINFO.ITEMNAME="+itemname;
				}else {}
				
				if(!factory.equals("") && factory!=null) {
					sql+=" AND ITEMINFO.FACTORY="+factory;
				}else {}
				
				if(!origin.equals("") && origin!=null) {
					sql+=" AND ITEMINFO.ORIGIN="+origin;
				}else {}
				
				if(!stDate.equals("") && stDate!=null && !endDate.equals("") && endDate!=null) {
					sql+=" AND BETWEEN "+stDate+" AND "+endDate;
				}else {}
			}
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum = rs.getInt(1);
			return maxnum;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<storageVo> storageList(int startRow,int endRow, String bigCate,String smallCate,String avail,String itemname,String factory,String origin,String stDate,String endDate){
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql="SELECT * FROM "+
					"("+
					" SELECT AA.*,ROWNUM RNUM FROM"+
					" ("+
					" 	SELECT * FROM ITEMINFO INNER JOIN CATEGORY "+
					"   ON ITEMINFO.CATID=CATEGORY.CATID";
		System.out.println(bigCate);
		if(!bigCate.equals("전체") && smallCate.equals("전체")) {
			
			if(bigCate.equals("채소")) {
				sql += " WHERE CATEGORY.CATID LIKE '111000%'";
			}else if(bigCate.equals("과일")) {
				sql += " WHERE CATEGORY.CATID LIKE '222000%'";
			}else if(bigCate.equals("수산")) {
				sql += " WHERE CATEGORY.CATID LIKE '333000%'";
			}else if(bigCate.equals("정육")) {
				sql += " WHERE CATEGORY.CATID LIKE '444000%'";
			}else if(bigCate.equals("완제품")) {
				sql += " WHERE CATEGORY.CATID LIKE '555000%'";
			}else if(bigCate.equals("음료")) {
				sql += " WHERE CATEGORY.CATID LIKE '666000%'";
			}
			
			if(avail.equals("재고")) {
				sql+=" AND ITEMINFO.AVAIL=1";
			}else if(avail.equals("매진")) {
				sql+=" AND ITEMINFO.AVAIL=0";}else {}
			
			if(!itemname.equals("") && itemname!=null) {
				sql+=" AND ITEMINFO.ITEMNAME="+itemname;
			}else {}
			
			if(!factory.equals("") && factory!=null) {
				sql+=" AND ITEMINFO.FACTORY="+factory;
			}else {}
			
			if(!origin.equals("") && origin!=null) {
				sql+=" AND ITEMINFO.ORIGIN="+origin;
			}else {}
			
			if(!stDate.equals("") && stDate!=null && !endDate.equals("") && endDate!=null) {
				sql+=" AND ITEMINFO.STOREDATE BETWEEN "+stDate+" AND "+endDate;
			}else {}
			
			
		}else if(!bigCate.equals("전체") && !smallCate.equals("전체")) {
			sql+=" WHERE CATEGORY.CATNAME='"+smallCate+"'";
			
			if(avail.equals("재고")) {
				sql+=" AND ITEMINFO.AVAIL=1";
			}else if(avail.equals("매진")) {
				sql+=" AND ITEMINFO.AVAIL=0";}else {}
			
			if(!itemname.equals("") && itemname!=null) {
				sql+=" AND ITEMINFO.ITEMNAME="+itemname;
			}else {}
			
			if(!factory.equals("") && factory!=null) {
				sql+=" AND ITEMINFO.FACTORY="+factory;
			}else {}
			
			if(!origin.equals("") && origin!=null) {
				sql+=" AND ITEMINFO.ORIGIN="+origin;
			}else {}
			
			if(!stDate.equals("") && stDate!=null && !endDate.equals("") && endDate!=null) {
				sql+=" AND BETWEEN "+stDate+" AND "+endDate;
			}else {}
			
		}else if(bigCate.equals("전체")) {
			if(avail.equals("재고")) {
				sql+=" WHERE ITEMINFO.AVAIL=1";
			}else if(avail.equals("매진")) {
				sql+=" WHERE ITEMINFO.AVAIL=0";}else {}
			
			if(!itemname.equals("") && itemname!=null) {
				sql+=" AND ITEMINFO.ITEMNAME="+itemname;
			}else {}
			
			if(!factory.equals("") && factory!=null) {
				sql+=" AND ITEMINFO.FACTORY="+factory;
			}else {}
			
			if(!origin.equals("") && origin!=null) {
				sql+=" AND ITEMINFO.ORIGIN="+origin;
			}else {}
			
			if(!stDate.equals("") && stDate!=null && !endDate.equals("") && endDate!=null) {
				sql+=" AND BETWEEN "+stDate+" AND "+endDate;
			}else {}
		}
		
		sql += " ORDER BY ITEMID DESC "+
				")AA "+
				") WHERE RNUM>=? AND RNUM<=?";
		try {
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<storageVo> list = new ArrayList<storageVo>();
			while(rs.next()) {
				int itemid = rs.getInt("itemid");
				String itemname1 = rs.getString("itemname");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				String factory1 = rs.getString("factory");
				String origin1 = rs.getString("origin");
				int stock = rs.getInt("stock");
				Date expire = rs.getDate("expire");
				Date storedate = rs.getDate("storedate");
				String image = rs.getString("image");
				int avail1 = rs.getInt("avail");
				String catname = rs.getString("catname");
				storageVo vo = new storageVo(itemid,itemname1,catid,price,factory1,origin1,stock,expire,storedate,image,avail1,catname);
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
