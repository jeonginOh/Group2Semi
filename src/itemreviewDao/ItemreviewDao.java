package itemreviewDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import semiVo.ItemreviewVo;

public class ItemreviewDao {
	private static ItemreviewDao instance=new ItemreviewDao();
	private ItemreviewDao() {}
	public static ItemreviewDao getInstance() {
		return instance;
	}
	public int review_insert(ItemreviewVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			String sql="insert into itemreview values(?,?,?,?,?,?,?,sysdate)";
								//리뷰pk,물품fk.,회원fk,제목,이미지,내용,별점,작성일
								
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 6);//(시퀀스로)
			pstmt.setInt(2, vo.getItemid());
			pstmt.setInt(3, vo.getMemid());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5,vo.getImage());
			pstmt.setString(6, vo.getContext());
			pstmt.setInt(7,vo.getStar());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con,pstmt,null);
		}
		
	}
	public ArrayList<ItemreviewVo> review_list(int itemid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from itemreview where itemid=? order by revid desc";
			//iteminfo테이블과 조인해서 상품명도 쓸 예정
			//memid테이블과 조인해서 사용자  아이디 or 이름 쓸 예정 ex)박정* or mauri****
			//(할수있다면..)
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			rs=pstmt.executeQuery();
			ArrayList<ItemreviewVo> list=new ArrayList<>();
			while(rs.next()) {
				ItemreviewVo vo=new ItemreviewVo(rs.getInt("revid"),itemid,rs.getInt("memid"),
						rs.getString("title"),rs.getString("image"),rs.getString("context"),rs.getInt("star"),rs.getDate("revdate"));
				//title,image,context,star,revdate만 사용할 예정이지만 혹시 몰라 모두 조회하게 하였음
				list.add(vo);
				
				
			}return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public ArrayList<ItemreviewVo> review_alllist(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from itemreview where itemid=? order by revid desc";
			//iteminfo테이블과 조인해서 상품명도 쓸 예정
			//memid테이블과 조인해서 사용자  아이디 or 이름 쓸 예정 ex)박정* or mauri****
			//(할수있다면..)
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			ArrayList<ItemreviewVo> list=new ArrayList<>();
			while(rs.next()) {
				ItemreviewVo vo=new ItemreviewVo(rs.getInt("revid"),rs.getInt("itemid"),rs.getInt("memid"),
						rs.getString("title"),rs.getString("image"),rs.getString("getcontext"),rs.getInt("star"),rs.getDate("revdate"));
				//title,image,context,star,revdate만 사용할 예정이지만 혹시 몰라 모두 조회하게 하였음
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
