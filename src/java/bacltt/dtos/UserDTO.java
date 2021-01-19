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
public class UserDTO implements Serializable{
    private String userID;
    private String passwword;
    private String fullname;
    private String address;
    private String phone;
    private String email;
    private String roleID;

    public UserDTO() {
    }
    public UserDTO(String userID, String passwword, String fullname,String roleID){
        this.userID = userID;
        this.passwword = passwword;
        this.fullname = fullname;
        this.roleID = roleID;
    }
    public UserDTO(String userID, String passwword, String fullname, String address, String phone, String email, String roleID) {
        this.userID = userID;
        this.passwword = passwword;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.roleID = roleID;
    }
    public UserDTO(String userID, String fullname, String roleID, String email, String address) {
        this.userID = userID;
        this.fullname = fullname;
        this.roleID = roleID;
        this.address = address;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswword() {
        return passwword;
    }

    public void setPasswword(String passwword) {
        this.passwword = passwword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    
}
