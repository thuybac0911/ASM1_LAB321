/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Thúy Bắc
 */
public class DBUtil {
    public static Connection getConnection() throws NamingException, SQLException{
        Connection con=null;
        Context context=new InitialContext();
        Context end = (Context)context.lookup("java:comp/env");
        DataSource ds=(DataSource)end.lookup("DBCon");
        con=ds.getConnection();
        return con;
    }
}
