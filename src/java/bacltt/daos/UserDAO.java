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
import javax.naming.NamingException;

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
    
    public UserDTO checkLogin(String userID,String password) throws SQLException, NamingException{
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
    
    public UserDTO checkLoginGG(String userid) throws SQLException, NamingException {
        UserDTO result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {

                String sql = "SELECT Fullname,RoleID,Email,Address "
                        + "FROM tblUsers "
                        + "WHERE userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("Fullname");
                    String roleid = rs.getString("RoleID");
                    String email = rs.getString("Email");
                    result = new UserDTO(userid, fullname, roleid, email,"");
                }

            }
        }  finally {
            closeConnection();

        }
        return result;
    }
    
    public void createUserGG(UserDTO user) throws SQLException, NamingException {
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {

                String sql = "INSERT INTO tblUsers(Fullname,userID,roleID,email) VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getFullname());
                stm.setString(2, user.getUserID());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getEmail());
                stm.executeUpdate();

            }
        }  finally {
            closeConnection();
        }

    }
}
