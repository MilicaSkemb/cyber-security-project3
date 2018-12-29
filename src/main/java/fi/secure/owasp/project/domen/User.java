/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.domen;

import java.util.Date;

/**
 *
 * @author milicas
 */
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private String token;
    private String about;
    private String site;
    private Date created_at;

    public User(String username, String password, String email, String token, String about, String site, Date created_at) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
        this.about = about;
        this.site = site;
        this.created_at = created_at;
    }

    public User(long id, String username, String password, String email, String token, String about, String site, Date created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
        this.about = about;
        this.site = site;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    
}
