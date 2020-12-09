package test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
  	Context initContext = new InitialContext();
	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
 */
public class DBCPBean {
	private static DataSource ds;
	//static 블록 - static 멤버변수를 초기화 할때 사용
	static {
		try {
			System.out.println("static블록 실행..");
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException{
		Connection con = ds.getConnection();
		return con;
	}
	
	public static void close(Connection con,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public static void close(Connection con) {
		try {
			if(con!=null) {
				con.close();
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
