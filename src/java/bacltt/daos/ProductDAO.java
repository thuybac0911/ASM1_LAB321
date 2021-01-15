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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<ProductDTO> findProductByCateID(String cateID) throws Exception{
        List<ProductDTO> result = null;
        String name, id,description,image;
        float price;
        int quantity;
        Date createDate;
        ProductDTO dto = null;
        try {
            String sql ="SELECT ProductID, ProductName,Price,Quantity,Description,Image,CreateDate "
                    + "FROM tblProducts where CateID=? AND IsDeleted = 0 ";
            conn= DBUtil.getConnection();
            stm=conn.prepareStatement(sql);
            stm.setString(1, cateID);
            rs=stm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                id = rs.getString("ProductID");
                name = rs.getString("ProductName");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                description = rs.getString("Description");
                image = rs.getString("Image");
                createDate = rs.getDate("CreateDate");
                dto = new ProductDTO(id, name, price, quantity, description, image, createDate);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    
    public List<ProductDTO> getListProduct(String productName,String cateID) throws SQLException, ClassNotFoundException{
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if(conn!=null){
                String sql = "SELECT ProductID,ProductName,Price,Quantity,Description,Image,CreateDate, CateID " 
                            + "FROM tblProducts A " 
                            + "WHERE ProductName LIKE ? AND CateID LIKE ?  " 
//                            + "AND Price >= ?  AND Price <= ? "
                            + "AND IsDeleted=0 " 
                            + "ORDER BY CreateDate DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%"+productName+"%");
                stm.setString(2, "%"+cateID+"%");
//                stm.setString(3, min);
//                stm.setString(4, max);
                rs = stm.executeQuery();
                while(rs.next()){
                    String productID = rs.getString("ProductID");
                    productName = rs.getString("ProductName");
                    String des = rs.getString("Description");
                    String image = rs.getString("Image");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    Date createDate = rs.getDate("CreateDate");
                    cateID = rs.getString("CateID");
                    list.add(new ProductDTO(productID, productName, price, quantity, des, image, createDate, 0, cateID));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
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
    public void deleteProduct(String id) throws SQLException, ClassNotFoundException{
        try {
            conn = DBUtil.getConnection();
            if(conn!=null){
                String sql = "UPDATE tblProducts "
                        + "SET IsDeleted = 1 "
                        + "WHERE ProductID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
}
