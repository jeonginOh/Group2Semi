package itemreviewDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import oracle.jdbc.driver.DBConversion;
import semiVo.ItemreviewVo;

public class ItemreviewDao {
	private static ItemreviewDao instance = new ItemreviewDao();

	private ItemreviewDao() {
	}

	public static ItemreviewDao getInstance() {
		return instance;
	}

	public int review_insert(ItemreviewVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into itemreview values(revieid_seq.nextval,?,?,?,?,?,?,sysdate)";
			// 리뷰pk,물품fk.,회원fk,제목,이미지,내용,별점,작성일
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getItemid());
			pstmt.setInt(2, vo.getMemid());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getImage());
			pstmt.setString(5, vo.getContext());
			pstmt.setInt(6, vo.getStar());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int review_delete(int revid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from itemreview where revid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, revid);
			return pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}

	}

	public ItemreviewVo review_listupdate(int revid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from itemreview where revid=?";
			// iteminfo테이블과 조인해서 상품명도 쓸 예정
			// memid테이블과 조인해서 사용자 아이디 or 이름 쓸 예정 ex)박정* or mauri****
			// (할수있다면..)
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, revid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ItemreviewVo vo = new ItemreviewVo(rs.getInt("revid"), rs.getInt("itemid"), rs.getInt("memid"),
						rs.getString("title"), rs.getString("image"), rs.getString("context"), rs.getInt("star"),
						rs.getDate("revdate"));
				// title,image,context,star,revdate만 사용할 예정이지만 혹시 몰라 모두 조회하게 하였음
				return vo;

			} else {
				return null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	public ArrayList<ItemreviewVo> review_list2(int itemid, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from " + "(" + "  select aa.*,rownum rnum from" + "  ( "
				+ "    select * from itemreview " + "   where itemid=?" + "    order by revid desc " + "  )aa "
				+ ") where rnum>=? and rnum<=?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ItemreviewVo> list = new ArrayList<ItemreviewVo>();
			while (rs.next()) {

				ItemreviewVo vo = new ItemreviewVo(rs.getInt("revid"), itemid, rs.getInt("memid"),
						rs.getString("title"), rs.getString("image"), rs.getString("context"), rs.getInt("star"),
						rs.getDate("revdate"));
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	public int getCount(int itemid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(count(itemid),0) cnt from itemreview";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	public int review_update(ItemreviewVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update itemreview set title=?, context=?, image=?, star=? where revid=?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContext());
			pstmt.setString(3, vo.getImage());
			pstmt.setInt(4, vo.getStar());
			pstmt.setInt(5, vo.getRevid());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public double avestar(int itemid) {// 평균별점구하기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select round(sum(star)/count(itemid),1) ave from itemreview where itemid=?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			rs = pstmt.executeQuery();
			rs.next();
			double ave = rs.getDouble(1);
			return ave;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}

	}

	public ArrayList<String> userName(int itemid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from memberinfo NATURAL join itemreview where itemid=? order by revid desc";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			rs = pstmt.executeQuery();

			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				String username = rs.getString(1);
				list.add(username);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	public String memId(int memid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from memberinfo NATURAL join itemreview where memid=? order by revid desc";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String memberid = rs.getString(1);
				return memberid;
			} else {
				return null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	public String itemname(int itemid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select itemname from iteminfo NATURAL join itemreview where itemid=?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String itemname = rs.getString(1);
				return itemname;
			} else {
				return null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
