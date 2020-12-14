package com.jeon.Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import db.DBCPBean;
import semiVo.MemberinfoVo;

/**
 * 싱글톤 패턴
 */
public class MemberinfoDao {
    private MemberinfoDao() {
    }

    private static MemberinfoDao instance = new MemberinfoDao();

    public static MemberinfoDao getInstance() {
        return instance;
    }
    /**
     * memid가 존재하는지 확인한다.(0 이상)
     * @param map id, pwd
     * @return 0< memid
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
                pstmt2 = conn.prepareStatement("select memid from memberinfo where id=? and pwd=?");
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

    //int <p>0 회원없음 <p>1=일반회원 <p>2=관리자 <p>-1=탈퇴회원
    //삭제시 이름 앞에 숫자 붙인 뒤 비활성 처리
    //진짜 삭제 기능도 만들어야 함
    public void delete() {
        //TODO:
        
    }
    public void tempUser() {
        //TODO:
    }


    /**
     * @param map 조회할 String, String 값
     * @return int <p>0회원없음 <p>1=일반회원 <p>2=관리자 <p>-1=탈퇴회원
     */
    public int check(HashMap<String, String> map) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res=null;
        String key = map.keySet().iterator().next();
        System.out.println(key);
        System.out.println(map.get(key));
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("select status from memberinfo where "+key+"=?");
            pstmt.setString(1, map.get(key));
            res=pstmt.executeQuery();
            if (res.next()) {
                System.out.println(res.getInt(1));
                return res.getInt("status");
            }else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }


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
            int res = pstmt.executeUpdate();
            if (res==1) return getMemId(vo.getId());
            else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }






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
    public String crypt(byte[] b) {// pw단방향 암호화
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
