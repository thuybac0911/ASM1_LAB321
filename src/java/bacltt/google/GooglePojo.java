/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.google;

/**
 *
 * @author Thúy Bắc
 */
public class GooglePojo {
    private String id;
    private String email;
    private boolean verified_email;
    private String given_name;
    private String family_name;
    private String link;
    private String picture;

    public GooglePojo() {
    }

    public GooglePojo(String id, String email, boolean verified_email, String given_name, String family_name, String link, String picture) {
        this.id = id;
        this.email = email;
        this.verified_email = verified_email;
        this.given_name = given_name;
        this.family_name = family_name;
        this.link = link;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
}
