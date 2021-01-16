/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Thúy Bắc
 */
public class OrderDTO implements Serializable{
    private String orderID;
    private String userID;
    private float totalPrice;
    private Date dateOfCreate;
    private String payment;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, float totalPrice, Date dateOfCreate, String payment, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.dateOfCreate = dateOfCreate;
        this.payment = payment;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
