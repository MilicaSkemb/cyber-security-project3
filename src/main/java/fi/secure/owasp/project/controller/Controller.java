/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.controller;

import com.google.gson.Gson;
import fi.secure.owasp.project.response.Response;

/**
 *
 * @author milicas
 */
public abstract class Controller {
    
    public static String response(spark.Response res, Response response) {
        res.status(response.getCode());
        return new Gson().toJson(response, response.getClass());
    }
    
}
