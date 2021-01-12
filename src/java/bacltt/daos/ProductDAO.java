/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.daos;

import bacltt.dtos.ProductDTO;
import bacltt.utils.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Thúy Bắc
 */
public class ProductDAO implements Serializable{
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public ProductDAO() {
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
    public boolean insert(ProductDTO product) throws SQLException, ClassNotFoundException{
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            if(conn!=null){
                String sql = "INSERT INTO tblProducts(ProductID,ProductName,Price,Quantity,Description,Image,CreateDate,IsDeleted,CateID) "
                        + "VALUES(?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, product.getProductID());
                stm.setString(2, product.getProductName());
                stm.setFloat(3, product.getPrice());
                stm.setInt(4, product.getQuantity());
                stm.setString(5, product.getDescription());
                stm.setString(6, product.getImage());
                stm.setTimestamp(7, new Timestamp(product.getCreateDate().getTime()));
                stm.setInt(8, product.isIsDeleted());
                stm.setString(9, product.getCateID());
                check = stm.executeUpdate() !=0 ? true:false;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
