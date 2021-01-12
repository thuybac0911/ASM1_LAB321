/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.daos;

import bacltt.dtos.UserDTO;
import bacltt.utils.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thúy Bắc
 */
public class UserDAO implements Serializable{
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public UserDAO() {
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
    
    public UserDTO checkLogin(String userID,String password) throws ClassNotFoundException, SQLException{
        UserDTO user = null;
        try {
            conn = DBUtil.getConnection();
            if(conn != null){
                String sql = "SELECT Fullname,RoleID "
                        + "FROM tblUsers "
                        + "WHERE UserID=? AND Password =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs=stm.executeQuery();
                if(rs.next()){
                    String fullname = rs.getString("Fullname");
                    String role = rs.getString("RoleID");
                    user = new UserDTO(userID, password, fullname, role);
                }
            }
        } finally {
            closeConnection();
        }
        return user;
    }
}
