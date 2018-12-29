/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.controller;

import fi.secure.owasp.project.database.Broker;
import fi.secure.owasp.project.domen.User;
import fi.secure.owasp.project.response.BadInputResponse;
import fi.secure.owasp.project.response.UserResponse;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author milicas
 */
public class AuthController extends Controller {
    
    public static String login(spark.Request req, spark.Response res) {
        
        String[] needed = {"username", "password"};
        
        for (String field : needed) {
            if (req.queryParams(field) == null || req.queryParams(field).isEmpty()) {
                return response(res, new BadInputResponse());
            }
        }
        
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        
        Broker broker = Broker.getInstance();
        User user = broker.loginUser(username, password);
        
        if (user == null)
            return response(res, new BadInputResponse("Wrong username or password"));
        
        return response(res, new UserResponse(user, 200));
    }
    
    public static String register(spark.Request req, spark.Response res) {
        
        String[] needed = {"username", "password", "email", "site", "about"};
        
        for (String field : needed) {
            if (req.queryParams(field) == null || req.queryParams(field).isEmpty()) {
                return response(res, new BadInputResponse());
            }
        }
        
        if (!EmailValidator.getInstance().isValid(req.queryParams("email"))) {
            return response(res, new BadInputResponse("Invalid email address"));
        }
        
        Broker broker = Broker.getInstance();
        
        if (!broker.usernameAvailable(req.queryParams("username"))) {
            return response(res, new BadInputResponse("Username already taken"));
        }
        
        Date now = new Date();
        String token = UUID.randomUUID().toString();
        
        
        User user = new User(req.queryParams("username"), 
                req.queryParams("password"), 
                req.queryParams("email"), 
                token,
                req.queryParams("about"), 
                req.queryParams("site"), 
                now);
        
        
        long id = broker.addUser(user);
        user.setId(id);
        
        return response(res, new UserResponse(user, 201));
        
    }
    
}
