/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thúy Bắc
 */
public class DBUtil implements Serializable{
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sinhvien";
        conn  = DriverManager.getConnection(url, "thuybac", "123456");
        return conn;
    }
}
