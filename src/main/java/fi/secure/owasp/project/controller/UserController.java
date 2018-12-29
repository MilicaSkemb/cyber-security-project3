/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.controller;

import static fi.secure.owasp.project.controller.Controller.response;
import fi.secure.owasp.project.database.Broker;
import fi.secure.owasp.project.domen.User;
import fi.secure.owasp.project.response.BadInputResponse;
import fi.secure.owasp.project.response.ErrorResponse;
import fi.secure.owasp.project.response.UserResponse;

/**
 *
 * @author milicas
 */
public class UserController extends Controller {
    public static String get(spark.Request req, spark.Response res){
        String user_id = req.params(":id");
        long id = Long.parseLong(user_id);
        Broker broker = Broker.getInstance();
        User user = broker.getUserByID(id);
        
        if (user == null)
            return response(res, new ErrorResponse(404, "User not found"));
        //return user.getSite();
        return response(res, new UserResponse(user, 200));  
    }
    
    public static String getSite(spark.Request req, spark.Response res){
        String user_id = req.params(":id");
        long id = Long.parseLong(user_id);
        Broker broker = Broker.getInstance();
        User user = broker.getUserByID(id);
        
        if (user == null)
            return response(res, new ErrorResponse(404, "User not found"));
        
        res.type("text/html");
        return user.getSite();
    }
}
