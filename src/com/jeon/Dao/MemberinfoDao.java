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
    public int getMemId(HashMap<String, String> map) {
        String id = map.get("id");
        byte[] pwd = map.get("pwd").getBytes();
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
                byte[] tmp = new byte[pwd.length+salt.length];
                System.arraycopy(pwd, 0, salt, 0, pwd.length);
                System.arraycopy(salt, 0, salt, pwd.length, salt.length);
                String cryptedpwd = crypt(tmp);
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







    private String makeSalt() {
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
    private static String crypt(byte[] b) {
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
    private static String crypt(String s) {	// pw단방향 암호화
        return crypt(s.getBytes());
    }
}
