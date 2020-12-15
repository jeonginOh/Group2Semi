package com.jeon.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import db.DBCPBean;
import semiVo.LoginauthVo;

public class LoginauthDao {
    final int expiredate=7;

    private LoginauthDao() {}
    private static LoginauthDao instance = new LoginauthDao();
    public static LoginauthDao getInstance() {return instance;}

    /**새로운 토큰 생성 */
    public int insert(LoginauthVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("insert into loginauth values(loginauth_seq.nextval,?,?,?,0,sysdate)");
            pstmt.setString(1, vo.getToken());
            pstmt.setInt(2, vo.getMemid());
            pstmt.setString(3, vo.getIdentifier());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }
    

    /**
     * 이전 토큰 폐기 후 새로운 토큰 발행.
     * uuid(토큰)만 바뀌고 identifier는 바뀌지 않는다.
     * @param vo
     * @return new token
     */
    public String renew(LoginauthVo vo) {
        expire(vo.getToken());
        String token = UUID.randomUUID().toString();
        vo.setToken(token);//새로운 uuid 입력
        if (insert(vo)==1) return token;
        else return null;
    }


    /**
     * token을 폐기한다.(per=-1)
     * @param token
     */
    public void expire(String token) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("update loginauth set per=-1 where token=?");
            pstmt.setString(1, token);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            //new login
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }

    /**
     * 오래된 버려진 값들을 폐기
     */
    public void purge() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("update loginauth set per=-1 where regdate<=sysdate-?");
            pstmt.setInt(1, expiredate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }


    //TODO:
    public void find(String token, String identifier) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("sql");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }
    //TODO:
    public void getlastlogin() {}
}
