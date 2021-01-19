/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.daos;

import bacltt.dtos.LogDTO;
import bacltt.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;

/**
 *
 * @author Thúy Bắc
 */
public class LogDAO {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public LogDAO() {
        conn = null;
        stm = null;
        rs=null;
                
    }
    private void closeConnection() throws SQLException{
        if(rs!=null) 
            rs.close();
        if(stm!=null) 
            stm.close();
        if(conn!=null) 
            conn.close();
    }
    public void insertLog(LogDTO log) throws SQLException, NamingException {
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblLogs(LogID,ProductID,UserID,Action,Description,DateAction) VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, log.getLogID());
                stm.setString(2, log.getProductID());
                stm.setString(3, log.getUserID());
                stm.setString(4, log.getAction());
                stm.setString(5, log.getDescription());
                stm.setTimestamp(6, new Timestamp(log.getDateAction().getTime()));
                stm.executeUpdate();
            }
        }  finally {
            closeConnection();
        }

    }
    
    public String getLastLogIDByProductID(String productID) throws Exception{
        String id = null;
        try {
            conn = DBUtil.getConnection();
            String sql ="SELECT LogID "
                    + "FROM tblLogs "
                    + "WHERE DateAction = (SELECT MAX(DateAction) FROM tblLogs where ProductID = ?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            if(rs.next()){
                id = rs.getString("LogID");
            }
        } finally {
            closeConnection();
        }
        return id;
    }
}
