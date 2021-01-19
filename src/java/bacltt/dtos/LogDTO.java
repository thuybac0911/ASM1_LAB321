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
public class LogDTO implements Serializable{
    private String logID;
    private String productID;
    private String userID;
    private String action;
    private String description;
    private Date dateAction;

    public LogDTO() {
    }

    public LogDTO(String logID, String productID, String userID, String action, String description, Date dateAction) {
        this.logID = logID;
        this.productID = productID;
        this.userID = userID;
        this.action = action;
        this.description = description;
        this.dateAction = dateAction;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }
    
}
