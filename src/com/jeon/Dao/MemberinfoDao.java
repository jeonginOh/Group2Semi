/* 
 * memberinfo 테이블의 입출력을 관리함
 * 메소드에서, memid는 0이면 존재하지 않고, -1이면 에러
 * status는 -1 탈퇴, 0은 존재하지 않는다.(휴먼에러), 1 일반회원, 2 관리자, 3 임시회원
 * 
 */
package com.jeon.Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import db.DBCPBean;
import semiVo.MemberinfoVo;

/**
 * 싱글톤 패턴
 */
public class MemberinfoDao {
    private MemberinfoDao() {}
    private static MemberinfoDao instance = new MemberinfoDao();
    public static MemberinfoDao getInstance() {return instance;}
    public final static int DELETED=-1;
    public final static int MEMBER=1;
    public final static int ADMIN=2;
    public final static int TEMPUSER=3;



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

    public boolean isTempMember(int memid) {
        return getStatus(memid)==TEMPUSER;
    }
    public boolean isMember(int memid) {
        return getStatus(memid)==MEMBER;
    }
    public boolean isDeleted(int memid) {
        return getStatus(memid)==DELETED;
    }
    public boolean isAdmin(int memid) {
        return getStatus(memid)==ADMIN;
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
            // String sql = "select id from memberinfo where "+type+"=? and status in(0,1)";
            String sql = "select id from memberinfo where "+type+"=? and status=1";
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
    public String getId(int memid) {
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
            // String sql = "update memberinfo set email=?, addr=?, phone=?, point=?, status=? where memid=?";
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
    
/*---------------------------------------------------------------------
    관리자페이지
---------------------------------------------------------------------*/
//#region 리스트


/**
 * 
 * @param search 0-1 type, search 2-3 age 4-5 regdate 6-7 point
 * @param startrow
 * @param endrow
 * @param order
 * @param orderby
 * @return
 */
public ArrayList<MemberinfoVo> list(String[] searches, int startrow, int endrow, String order, String orderby) {
    Connection conn = null;
    ResultSet res = null;
    PreparedStatement pstmt = null;
    ArrayList<MemberinfoVo> list = new ArrayList<>();
    String[] querys = {
        "select * from (select rownum rnum, mi.* from (select * from memberinfo order by "+orderby+" "+order+") mi) where rnum between ? and ?",
        "select * from (select rownum rnum, mi.* from (select * from memberinfo where ",
        "order by "+orderby+" "+order+") mi) where rnum between ? and ?"
    };
    try {
        conn = DBCPBean.getConn();
        // String sql = "select * from (select rownum rnum, mi.* from (select * from memberinfo where regexp_like (?, ?) order by memid "+order+") mi) where rnum between ? and ?";
        pstmt = listpstmt(conn, searches, startrow, endrow, querys);
        res = pstmt.executeQuery();
        while(res.next()) {
            list.add(new MemberinfoVo(res.getInt("memid"), res.getString("id"), null, null, res.getString("age"), res.getString("email"), res.getString("addr"), res.getDate("regdate"), res.getString("phone"), res.getInt("point"), res.getInt("status")));
        }
        if (list.size()!=0) return list;
        else return null;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    } finally {
        DBCPBean.close(conn, res, pstmt);
    }
}
public ArrayList<MemberinfoVo> list(int startrow, int endrow, String order, String orderby) {
    return list(null, startrow, endrow, order, orderby);
}

// private PreparedStatement listpstmt(Connection conn, String type, String search, int startrow, int endrow, String order)
//         throws SQLException {
//     PreparedStatement pstmt = null;
//     if (type==null || type.isBlank() || search==null || search.isBlank()) {
//         pstmt = conn.prepareStatement("select * from (select rownum rnum, mi.* from (select * from memberinfo order by memid "+order+") mi) where rnum between ? and ?");
//         pstmt.setInt(1, startrow);
//         pstmt.setInt(2, endrow);
//     } else {
//         pstmt = conn.prepareStatement("select * from (select rownum rnum, mi.* from (select * from memberinfo where regexp_like (?, ?) order by memid "+order+") mi) where rnum between ? and ?");
//         pstmt.setString(1, type);
//         pstmt.setString(2, search);
//         pstmt.setInt(3, startrow);
//         pstmt.setInt(4, endrow);
//     }
//     return pstmt;
// }

/**
 * 
 * @param conn
 * @param search
 * @param startrow
 * @param endrow
 * @param querys 0 for search==null 1 for pre, 2 for after
 * @return
 * @throws SQLException
 */
private PreparedStatement listpstmt(Connection conn, String[] search, int startrow, int endrow, String[] querys) throws SQLException {
    PreparedStatement pstmt = null;
    int count=0;
    if (search==null) {
        pstmt = conn.prepareStatement(querys[0]);
        // pstmt.setString(++count, orderby);
        if (startrow>0) pstmt.setInt(++count, startrow);
        if (endrow>0)pstmt.setInt(++count, endrow);
    } else {
        StringBuilder sql = new StringBuilder();
        sql.append(querys[1]);
        //#region 빈값제거
        
        ArrayList<String> arr = new ArrayList<>();
        if (search[0]!=null && search[1]!=null) {
            sql.append("regexp_like ("+search[0]+", ?) and ");
            arr.add(search[1]);
        }
        String[] cols = {"age", "regdate", "point"};
        for(int i=1; i<=cols.length; i++) {
            if (search[i*2]!=null || search[i*2+1]!=null) {
                sql.append(cols[i-1]);
                if (search[i*2]==null) {
                    sql.append("<=? ");
                    arr.add(search[i*2+1]);
                }else if (search[i*2+1]==null) {
                    sql.append(">=? ");
                    arr.add(search[i*2]);
                }else {
                    sql.append(" between ? and ? ");
                    arr.add(search[i*2]);
                    arr.add(search[i*2+1]);
                }
                sql.append("and ");
            }
        }
        // if (search[2]!=null || search[3]!=null) {
        //     sql.append("age");
        //     if (search[2]==null) {
        //         sql.append("<=? ");
        //         arr.add(search[3]);
        //     }else if (search[3]==null) {
        //         sql.append(">=? ");
        //         arr.add(search[2]);
        //     }else {
        //         sql.append(" between ? and ? ");
        //         arr.add(search[2]);
        //         arr.add(search[3]);
        //     }
        //     sql.append("and ");
        // }
        // if (search[4]!=null || search[5]!=null) {
        //     sql.append("regdate");
        //     if (search[4]==null) {
        //         sql.append("<=? ");
        //         arr.add(search[5]);
        //     }else if (search[5]==null) {
        //         sql.append(">=? ");
        //         arr.add(search[4]);
        //     }else {
        //         sql.append(" between ? and ? ");
        //         arr.add(search[4]);
        //         arr.add(search[5]);
        //     }
        //     sql.append("and ");
        // }
        // if (search[6]!=null || search[7]!=null) {
        //     sql.append("point");
        //     if (search[6]==null) {
        //         sql.append("<=? ");
        //         arr.add(search[7]);
        //     }else if (search[7]==null) {
        //         sql.append(">=? ");
        //         arr.add(search[6]);
        //     }else {
        //         sql.append(" between ? and ? ");
        //         arr.add(search[6]);
        //         arr.add(search[7]);
        //     }
        //     sql.append("and ");
        // }
        if (arr.size()>0) sql.delete(sql.length()-4, sql.length());
        else sql.delete(sql.length()-6, sql.length());
        if (querys[2]!=null) sql.append(querys[2]);
        pstmt = conn.prepareStatement(sql.toString());
        System.out.println("SQL: "+sql.toString());
        System.out.println("startrow:"+startrow);
        System.out.println("endrow:"+endrow);
        System.out.println("arrsize:"+arr.size());

        // for (;qs[count]!=null; count++) {
        //     pstmt.setString(count+c, qs[count]);
        //     // System.out.println(count+c+" "+ qs[count]);
        // }
        for (String string : arr) {
            System.out.println("arr:="+string);
        }
        for (int i=0; i<arr.size(); i++) {
            pstmt.setString(i+1, arr.get(i));
            System.out.println("index"+i+"arr="+arr.get(i));
        }
        int c = arr.size();
        // pstmt.setString(++count, orderby);
        // System.out.println(count + orderby);
        if (startrow>0) pstmt.setInt(++c, startrow);
        if (endrow>0)pstmt.setInt(++c, endrow);
        // System.out.println(count + startrow);
        // System.out.println(count + endrow);
        }
    return pstmt;
}


/**
 * 
 * @param search 0-1 type, search 2-3 age 4-5 regdate 6-7 point 8-9 status
 * @param startrow
 * @param endrow
 * @param order asc, desc
 * @param orderby 
 * @return
 */
public JSONObject jsonList(String[] searches, int startrow, int endrow, String order, String orderby) {
    Connection conn = null;
    ResultSet res = null;
    PreparedStatement pstmt = null;
    JSONObject json = new JSONObject();
    JSONArray list = new JSONArray();
    String[] querys = {
        "select * from (select rownum rnum, mi.* from (select * from memberinfo order by "+orderby+" "+order+") mi) where rnum between ? and ?",
        "select * from (select rownum rnum, mi.* from (select * from memberinfo where ",
        "order by "+orderby+" "+order+") mi) where rnum between ? and ?"
    };
    try {
        conn = DBCPBean.getConn();
        System.out.println("JSONLIST");
        pstmt = listpstmt(conn, searches, startrow, endrow, querys);
        res = pstmt.executeQuery();
        while(res.next()) {
            JSONObject memjson = new JSONObject();
            memjson.append("rnum", res.getInt("rnum"));
            memjson.append("memid", res.getInt("memid"));
            memjson.append("id", res.getString("id"));
            memjson.append("age", res.getString("age"));
            memjson.append("email", res.getString("email"));
            memjson.append("addr", res.getString("addr"));
            memjson.append("regdate", res.getDate("regdate"));
            memjson.append("phone", res.getString("phone"));
            memjson.append("point", res.getInt("point"));
            memjson.append("status", res.getInt("status"));
            list.put(memjson);
        }
        if (list.length()!=0) {
            json.put("list", list);
            return json;
        }
        else return null;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    } finally {
        DBCPBean.close(conn, res, pstmt);
    }
}
public JSONObject jsonList(int startrow, int endrow, String order, String orderby) {
    return jsonList(null, startrow, endrow, order, orderby);
}

// public int size(String type, String search) {
//     Connection conn = null;
//     PreparedStatement pstmt = null;
//     ResultSet res=null;
//     try {
//         conn = DBCPBean.getConn();
//         String sql = "select nvl(count(memid), 0) cnt from memberinfo";
//         if (search==null || search.isBlank() || type==null || type.isBlank()) pstmt = conn.prepareStatement(sql);
//         else {
//             sql += " where regexp_like (?, ?)";
//             pstmt = conn.prepareStatement(sql);
//             pstmt.setString(1, type);
//             pstmt.setString(2, search);
//         }
//         res = pstmt.executeQuery();
//         res.next();
//         return res.getInt(1);
//     } catch (SQLException e) {
//         e.printStackTrace();
//         return 0;
//     } finally {
//         DBCPBean.close(conn, pstmt, res);
//     }
// }

public int size(String[] search) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet res=null;
    try {
        conn = DBCPBean.getConn();
        StringBuilder sbsql = new StringBuilder();
        sbsql.append("select nvl(count(memid), 0) maxrow from memberinfo where");
        String[] querys = {
            "select nvl(count(memid), 0) cnt from memberinfo",
            "select nvl(count(memid), 0) cnt from memberinfo where ",
            null
        };
        System.out.println("SIZE-----");
        pstmt = listpstmt(conn, search, -1, -1, querys);
        res = pstmt.executeQuery();
        res.next();
        System.out.println(res.getInt(1));
        return res.getInt(1);
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    } finally {
        DBCPBean.close(conn, pstmt, res);
    }
}


/**
 * currently support - point, status
 * @param type
 * @param value
 * @param memids
 * @return
 */
public boolean batchChange(String type, String value, String[] memids) {
    Connection conn=null;
    PreparedStatement pstmt =null;
    try {
        conn = DBCPBean.getConn();
        conn.setAutoCommit(false);
        // String sql = "update memberinfo set email=?, addr=?, phone=?, point=?, status=? where memid=?";
        StringBuilder sb = new StringBuilder();
        sb.append("update memberinfo set ");
        if (type.equals("point")) {
            if (value.startsWith("+") || value.startsWith("-") || value.startsWith("/") || value.startsWith("*")) sb.append(type+"="+type+value);
            else sb.append(type+"="+value);
        }
        else sb.append(type+"="+value);
        sb.append(" where memid in(");
        for(int i=0; i<memids.length; i++) {
            sb.append("?, ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(")");
        System.out.println("batcheditsql="+sb.toString());
        pstmt = conn.prepareStatement(sb.toString());
        
        for(int i=0; i<memids.length; i++) {
            pstmt.setString(i+1, memids[i]);
        }
        if (pstmt.executeUpdate()==memids.length) {
            conn.commit();
            return true;
        } else {
            conn.rollback();
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        try {if (conn!=null) conn.rollback();}
        catch (SQLException e1) {e1.printStackTrace();}
        return false;
    } finally {
        DBCPBean.close(conn, pstmt);
    }
}

// public void test(String a, String b) {
//     Connection conn = null;
//     PreparedStatement pstmt = null;
//     ResultSet res = null;
//     try {
//         conn = DBCPBean.getConn();
//         pstmt = conn.prepareStatement("select nvl(count(memid), 0) cnt from memberinfo where regexp_like (?, ?) ");
//         pstmt.setString(1,a);
//         pstmt.setString(2,b);
//         res = pstmt.executeQuery();
//         while(res.next()) {
//             System.out.println(res.getString(1));
//         }
//     } catch (SQLException e) {
//         e.printStackTrace();
        
//     } finally {
//         DBCPBean.close(conn, pstmt, res);
//     }
// }


//#endregion
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
 * 임시회원을 만들고 memid를 리턴한다. 임시회원은 memid, id와 regdate, status=3만 존재한다.
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