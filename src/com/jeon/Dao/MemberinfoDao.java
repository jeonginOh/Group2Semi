package com.jeon.Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import db.DBCPBean;
import semiVo.MemberinfoVo;

/**
 * 싱글톤 패턴
 */
public class MemberinfoDao {
    private MemberinfoDao() {}
    private static MemberinfoDao instance = new MemberinfoDao();
    public static MemberinfoDao getInstance() {return instance;}
    /**
     * memid가 존재하는지 확인한다.(0 이상)
     * @param map id, pwd
     * @return memid
     * <p>-2 SQL ERROR <p>-1 NO ID <p>0 ID&PWD NOT MATCH
     */
    public int login(HashMap<String, String> map) {
        String id = map.get("id");
        String pwd = map.get("pwd");
        byte[] salt=null;
        Connection conn = null;
        //get salt
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet res1=null;
        ResultSet res2=null;
        //String pwd = crypt(map.get("pwd"));
        try {
            conn = DBCPBean.getConn();
            pstmt1=conn.prepareStatement("select salt from memberinfo where id=?");
            pstmt1.setString(1, id);
            res1 = pstmt1.executeQuery();
            if (res1.next()) {
                salt = res1.getString("salt").getBytes();
                String cryptedpwd = crypt(pwd, salt);
                pstmt2 = conn.prepareStatement("select memid from memberinfo where id=? and pwd=? and status>0");
                pstmt2.setString(1, id);
                pstmt2.setString(2, cryptedpwd);
                res2 = pstmt2.executeQuery();
                if (res2.next()) {
                    return res2.getInt("memid");
                }else return 0;
            }else return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        } finally {
            DBCPBean.close(conn, pstmt1, pstmt2, res1, res2);
        }
    }
    
    /**
     * status를 가져온다.
     * @param memid
     * @return
     */
    public int getStatus(int memid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select status from memberinfo where memid=?");
            pstmt.setInt(1, memid);
            res = pstmt.executeQuery();
            if(res.next()) {
                return res.getInt("status");
            }else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

    public boolean isMember(int memid) {
        return getStatus(memid)==1;
    }
    
    public boolean isDeleted(int memid) {
        return getStatus(memid)==-1;
    }


    //int <p>0 회원없음 <p>1=일반회원 <p>2=관리자 <p>-1=탈퇴회원
    /**
     * 삭제시 이름 앞에 숫자 붙인 뒤 비활성 처리<p>실제로 테이블에서 삭제되지는 않음<p>자동로그인도 폐기함
     * @param memid
     * @return true or false
     */
    public boolean delete(int memid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("update memberinfo set status=-1 where memid=?");
            pstmt.setInt(1, memid);
            if (pstmt.executeUpdate()>0) {
                LoginauthDao.getInstance().expire(memid);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

     /**
      * 중복확인용 메소드
      * @param type id, phone, email
      * @param value type's value
      * @return status <p>1=일반회원 <p>2=관리자 <p>-1=탈퇴회원
      */
    public int check(String type, String value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res=null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select status from memberinfo where "+type+"=?");
            pstmt.setString(1, value);
            res=pstmt.executeQuery();
            if (res.next()) {
                return res.getInt("status");
            }else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

    /**
     * 등록된 이메일, 전화번호로 id를 찾는 기능
     * @param type email || phone
     * @param value 
     * @return id
     */
    public String findId(String type, String value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            //status가 1인 회원(일반회원)을 대상으로 조회
            String sql = "select id from memberinfo where "+type+"=? and status in(0,1)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            res = pstmt.executeQuery();
            if (res.next()) {
                String id = res.getString("id");
                return id.replaceAll("(?<=.{4}).", "*");
            }else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }
    
    /**
     * id를 통해 memid를 가져온다
     * @param id
     * @return memid
     */
    public int getMemId(String id) {
        Connection conn=null;
        PreparedStatement pstmt =null;
        ResultSet res=null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select memid from memberinfo where id=?");
            pstmt.setString(1, id);
            res = pstmt.executeQuery();
            if (res.next()) return res.getInt(1);
            else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally{
            DBCPBean.close(conn, pstmt, res);
        }
    }

    /**
     * memid로 id를 가져옴
     * @param memid
     * @return {@code String} id
     */
    public String getid(int memid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select id from memberinfo where memid=?");
            pstmt.setInt(1, memid);
            res = pstmt.executeQuery();
            if(res.next()) return res.getString("id");
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

    public String getEmail(int memid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select email from memberinfo where memid=?");
            pstmt.setInt(1, memid);
            res = pstmt.executeQuery();
            if(res.next()) return res.getString("email");
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

    /**
     * 일단 다 뽑음(pwd, salt 제외)
     * @param memid
     * @return MemberinfoVo
     */
    public MemberinfoVo getVo(int memid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select * from memberinfo where memid=?");
            pstmt.setInt(1, memid);
            res = pstmt.executeQuery();
            if(res.next()) {
                return new MemberinfoVo(memid, res.getString("id"), null, null, res.getString("age"), res.getString("email"), res.getString("addr"), res.getDate("regdate"), res.getString("phone"), res.getInt("point"), res.getInt("status"));
            }else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

    /**
     * 
     * @param vo
     * @return 추가된 memid<p>0=추가되지 않음<p>-1=SQL에러
     */
    public int insert(MemberinfoVo vo) {
        Connection conn=null;
        PreparedStatement pstmt =null;
        try {
            conn = DBCPBean.getConn();
            String sql = "insert into memberinfo values(memberinfo_memid.nextval, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPwd());
            pstmt.setString(3, vo.getSalt());
            pstmt.setString(4, vo.getAge());
            pstmt.setString(5, vo.getEmail());
            pstmt.setString(6, vo.getAddr());
            pstmt.setString(7, vo.getPhone());
            pstmt.setInt(8, vo.getPoint());
            pstmt.setInt(9, vo.getStatus());
            if (pstmt.executeUpdate()==1) return getMemId(vo.getId());
            else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }


    /**
     * getVo에는 pwd, salt가 없기 때문에
     * @param memid
     * @param pwd
     * @return
     */
    public boolean newPwd(int memid, String pwd) {
        Connection conn=null;
        PreparedStatement pstmt =null;
        try {
            conn = DBCPBean.getConn();
            String sql = "update memberinfo set pwd=?, salt=? where memid=?";
            pstmt = conn.prepareStatement(sql);
            String salt = makeSalt();
            String newpwd = crypt(pwd, salt);
            pstmt.setString(1, newpwd);
            pstmt.setString(2, salt);
            pstmt.setInt(3, memid);
            if (pstmt.executeUpdate()==1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }

    /**
     * vo의 memid의 email, addr, phone을 수정함.
     * 수정할 수 있는게 별로 없다.
     * @param vo
     * @return
     */
    public boolean change(MemberinfoVo vo) {
        Connection conn=null;
        PreparedStatement pstmt =null;
        try {
            conn = DBCPBean.getConn();
            String sql = "update memberinfo set email=?, addr=?, phone=? where memid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getEmail());
            pstmt.setString(2, vo.getAddr());
            pstmt.setString(3, vo.getPhone());
            pstmt.setInt(4, vo.getMemid());
            if (pstmt.executeUpdate()==1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }
/**---------------------------------------------------------------------
 * 임시유저 관련 메소드
----------------------------------------------------------------------*/
/**
 * 1일 주기로 리셋되는 시퀀스
 * 
 * @return daily_seq.nextval
 */
private int getDaily_seq() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet res = null;
    try {
        conn = DBCPBean.getConn();
        pstmt = conn.prepareStatement("select daily_seq.nextval from dual");
        res = pstmt.executeQuery();
        if (res.next())
        return res.getInt(1);
        else return 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    } finally {
        DBCPBean.close(conn, pstmt, res);
    }
}
/**
 * 임시회원을 만들고 memid를 리턴한다. 임시회원은 memid, id와 regdate, status만 존재한다.
 * @param vo
 * @return memid
 */
public int newTempUser() {
    Connection conn=null;
    PreparedStatement pstmt =null;
    try {
        conn = DBCPBean.getConn();
        String sql = "insert into memberinfo values(memberinfo_memid.nextval, ?, null, null, null, null, null, sysdate, null, 0, 3)";
        pstmt = conn.prepareStatement(sql);
        String id = new SimpleDateFormat("YYMMdd").format(new Date())+getDaily_seq();
        pstmt.setString(1, id);
        if (pstmt.executeUpdate()==1) return getMemId(id);
        else return 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    } finally {
        DBCPBean.close(conn, pstmt);
    }
}


/**---------------------------------------------------------------------
 * 암호화 관련 메소드
----------------------------------------------------------------------*/
    /**
     * salt값을 랜덤하게 생성한다.
     * @return salt
     */
    public String makeSalt() {
        Random random = new Random();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			// byte 값을 Hex 값으로 바꾸기.
			sb.append(String.format("%02x",bytes[i]));
		}
		return sb.toString();
    }

    private String crypt(byte[] b) {// pw단방향 암호화
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(b);
            byte[] bytedata = md.digest();
            StringBuilder sb=new StringBuilder();
            for (int i=0; i<bytedata.length; i++) {
                sb.append(Integer.toString((bytedata[i] & 0xFF) + 256, 16).substring(1));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException nse){
            return null;
        }
    }

    public String crypt(String pwd, byte[] salt) {
        byte[] bpwd = pwd.getBytes();
        byte[] tmp = new byte[bpwd.length+salt.length];
        System.arraycopy(bpwd, 0, tmp, 0, bpwd.length);
        System.arraycopy(salt, 0, tmp, bpwd.length, salt.length);
        return crypt(tmp);
    }

    public String crypt(String pwd, String salt) {
        byte[] bsalt = salt.getBytes();
        return crypt(pwd, bsalt);
    }
}