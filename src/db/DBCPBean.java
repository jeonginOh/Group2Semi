package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCPBean {
    private static DataSource ds;
    static {
        Context initContext;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/myoracle");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    protected DBCPBean() {}
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
    /**
     * 
     * @param obj
     */
    public static void close(AutoCloseable...obj) {
        try {
            closer(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void closer(AutoCloseable...obj) throws Exception{
        for (AutoCloseable o : obj) {
            if (o instanceof ResultSet)
                if (o != null)
                    o.close();
        }
        for (AutoCloseable o : obj) {
            if (o instanceof Statement)
                if (o != null)
                    o.close();
        }
        for (AutoCloseable o : obj) {
            if (o instanceof Connection)
                if (o != null)
                    o.close();
        }
    }

}