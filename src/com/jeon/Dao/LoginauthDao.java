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

    
    public void expire(int memid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("update loginauth set per=-1 where memid=? and per=0");
            pstmt.setInt(1, memid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //new login
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }
    /**
     * 로그아웃용. memid와 identifier를 받아서 폐기한다.
     * @param memid
     * @param identifier
     */
    public void expire(int memid, String identifier) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBCPBean.getConn();
            pstmt = conn.prepareStatement("update loginauth set per=-1 where memid=? and identifier=? and per=0");
            pstmt.setInt(1, memid);
            pstmt.setString(2, identifier);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //new login
        } finally {
            DBCPBean.close(conn, pstmt);
        }
    }

    /**
     * 오래된 버려진 값들을 폐기
     * @euokyun
     * TODO:주기적으로 돌아갈 방법을 찾아야 함
     */
    public void purge() {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            pstmt1 = conn.prepareStatement("update loginauth set per=-1 where regdate<=sysdate-? and per=?");
            pstmt2 = conn.prepareStatement("update loginauth set per=-1 where regdate<=sysdate-? and per=?");
            pstmt1.setInt(1, expiredate);
            pstmt1.setInt(2, 0);
            pstmt2.setInt(1, 1);
            pstmt2.setInt(2, 1);
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPBean.close(conn, pstmt1, pstmt2, res);
        }
    }
    //TODO:로그인기능
    //token은 같은데 identifier가 다를 경우 사용자에게 경고메시지 출력

    /**
     * 
     * @param token
     * @param identifier
     * @return -1 해킹<p> 0 없음 <p> memid
     */
    public int autologin(String token, String identifier) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            //자동 로그인시도
            String sql = "select memid, identifier from loginauth where token=? and per=0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, token);
            res=pstmt.executeQuery();
            if (res.next()) {
                if(res.getString("identifier").equals(identifier)) {
                    return res.getInt("memid");
                }else {
                    //보안문제 발생 - 쿠키 유출
                    //memid에 관련된 모든 로그인테이블 폐기
                    expire(res.getInt("memid"));
                    return -1; 
                }
            }return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

    //TODO:
    public void getlastlogin() {}

    //======================================================================
    //여기서부터 token을 사용한 임시로그인용 메소드

    /**
     * 링크는 하루 후 로그인불가
     * @param token
     * @return
     */
    public int templogin(String token) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = DBCPBean.getConn();
            //자동 로그인시도
            String sql = "select memid, identifier from loginauth where token=? and per=1 and where regdate>=sysdate-1";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, token);
            res=pstmt.executeQuery();
            if (res.next()) {
                return res.getInt("memid");
            }return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBCPBean.close(conn, pstmt, res);
        }
    }

}