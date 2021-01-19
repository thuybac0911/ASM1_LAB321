/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.daos;

import bacltt.dtos.OrderDTO;
import bacltt.dtos.OrderDetailDTO;
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
public class OrderDAO implements Serializable{
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection()throws Exception{
        if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
    }

    public OrderDAO() {
        conn = null;
        stm = null;
        rs = null;
    }
    public String getLastOrderIDByUser(String userID) throws Exception{
        String id = null;
        try {
            conn = DBUtil.getConnection();
            String sql ="SELECT OrderID "
                    + "FROM tblOrders "
                    + "WHERE DateOfCreate = (SELECT MAX(DateOfCreate) FROM tblOrders where UserID = ?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            if(rs.next()){
                id = rs.getString("OrderID");
            }
        } finally {
            closeConnection();
        }
        return id;
    }
    
    public boolean inserOrder(OrderDTO order ) throws Exception{
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO tblOrders(OrderID,UserID,TotalPrice,DateOfCreate,Payment,Status) "
                    + "VALUES(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, order.getOrderID());
            stm.setString(2, order.getUserID());
            stm.setFloat(3, order.getTotalPrice());
            stm.setTimestamp(4, new Timestamp(order.getDateOfCreate().getTime()));
            stm.setString(5, order.getPayment());
            stm.setString(6, order.getStatus());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean insertOrderDetail(OrderDetailDTO dto )throws Exception{
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            String sql ="INSERT INTO tblOrderDetails(DetailID,OrderID,ProductID,Price,Quantity) "
                    + "VALUES(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getDetailID());
            stm.setString(2, dto.getOrderID());
            stm.setString(3, dto.getProductID());           
            stm.setFloat(4, dto.getPrice());
            stm.setInt(5, dto.getQuantity());            
            check = stm.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
        return check;
    }
    public void updateQuantity(int quantity,String productID) throws SQLException, ClassNotFoundException, Exception{
        try {
            conn = DBUtil.getConnection();
            if(conn!=null){
                String sql = "UPDATE tblProducts "
                        + "SET Quantity = Quantity - ?  "
                        + "WHERE ProductID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, productID);
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<OrderDTO> fillAllOrder() throws Exception{
        List<OrderDTO> result = null;
        String id, status;
        Date dayOfCreate;
        float totalPrice;
        OrderDTO dto=null;
        try {
            String sql = "SELECT OrderID,DateOfCreate,TotalPrice,Status "
                    + "FROM tblOrders";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            result= new ArrayList<>();
            while(rs.next()){
                id = rs.getString("OrderID");
                dayOfCreate = rs.getDate("DateOfCreate");
                totalPrice = rs.getFloat("TotalPrice");
                status = rs.getString("Status");
                dto = new OrderDTO(id, dayOfCreate,totalPrice, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<OrderDetailDTO> getListOrderDetails(String orderID) throws Exception{
        List<OrderDetailDTO> result = null;
        String name,image;
        float price;
        int quantity;
        OrderDetailDTO dto = null;
        try {
            String sql ="SELECT P.ProductName,OD.Quantity,OD.Price,P.Image\n" +
                        "FROM tblOrderDetails OD\n" +
                        "JOIN tblProducts P\n" +
                        "ON P.ProductID=OD.ProductID\n" +
                        "WHERE OrderID=?";
            conn= DBUtil.getConnection();
            stm=conn.prepareStatement(sql);
            stm.setString(1, orderID);
            rs=stm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                name = rs.getString("ProductName");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                image = rs.getString("Image");
                dto = new OrderDetailDTO(orderID, price, quantity, name, image);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
}
