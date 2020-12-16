package com.jeon.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import db.DBCPBean;
import semiVo.LoginauthVo;
/**
 * <p>1216 테이블의미변경 - 로그인기록용 테이블.
 * <p>모든 로그인은 여기 기록하고, 자동 로그인일 경우 cookie를 남기고 per=0을 준다.
 * <p>자동로그인 설정을 하지 않았으면 -1로 바로 expire
 * <p>임시로그인은 per=1 값을 준다.
 * <p>접속성공시 바로 renew로 expire 후 새로운 컬럼을 만든다.
 */
public class LoginauthDao {
    final int expiredate=7;

    private LoginauthDao() {}
    private static LoginauthDao instance = new LoginauthDao();
    public static LoginauthDao getInstance() {return instance;}

    //======================================================================
    //유저용 자동 로그인 기능.
    //per=0으로 초기화되고, -1이면 expire된 값임.

    /**새로운 토큰 생성 */
    public int insert(LoginauthVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("insert into loginauth values(loginauth_seq.nextval,?,?,?,?,sysdate)");
            pstmt.setString(1, vo.getToken());
            pstmt.setInt(2, vo.getMemid());
            pstmt.setString(3, vo.getIdentifier());
            pstmt.setInt(4, vo.getPer());
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
    //TODO:로그인기능
    //token은 같은데 identifier가 다를 경우 사용자에게 경고메시지 출력
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

    //======================================================================
    //여기서부터 임시로그인용 메소드
    // public int t_insert(LoginauthVo vo) {} 공유해서 사용한다.
    


}
