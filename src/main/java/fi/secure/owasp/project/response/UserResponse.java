/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.response;

import fi.secure.owasp.project.domen.User;

/**
 *
 * @author milicas
 */
public class UserResponse extends Response {
    
    private User user;
    
    public UserResponse(User user, int code) {
        super(true, 200);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
