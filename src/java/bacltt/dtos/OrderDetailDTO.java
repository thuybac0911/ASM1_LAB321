/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.dtos;

import java.io.Serializable;

/**
 *
 * @author Thúy Bắc
 */
public class OrderDetailDTO implements Serializable{
    private String detailID;
    private String orderID;
    private String productID;
    private float price;
    private int quantity;
    private String name ;
    private String image;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String detailID, String orderID, String productID, float price, int quantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailDTO(String orderID, float price, int quantity, String name, String image) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
