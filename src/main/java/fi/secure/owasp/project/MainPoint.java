/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project;

import com.google.gson.Gson;
import fi.secure.owasp.project.controller.AuthController;
import fi.secure.owasp.project.controller.UserController;
import fi.secure.owasp.project.database.Broker;
import fi.secure.owasp.project.response.ErrorResponse;
import fi.secure.owasp.project.response.Response;
import java.io.PrintWriter;
import java.io.StringWriter;
import spark.Spark;
import static spark.Spark.*;
/**
 *
 * @author milicas
 */
public class MainPoint {
    
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        
        port(8080);
        
        before("/*", (req, res) -> res.type("application/json"));
        get("/login", (req, res) ->  AuthController.login(req, res) );
        get("/register", (req, res) -> AuthController.register(req, res) );
      
        path("/user", () -> {
            before("/*", (req, res) -> {
                String check = MainPoint.checkToken(req.queryParams("token"));
                if (!check.isEmpty()) {
                    halt(401, check);
                }
            });
            get("/:id",
                        (req, res) -> UserController.get(req,res)
                );
            get("/:id/site",
                        (req, res) -> UserController.getSite(req,res)
                );
            get("/:id/about",
                        (req, res) -> UserController.getSite(req, null)
                );
        });
        
        

        notFound((req, res) -> {
            res.type("application/json");
            Response response = new ErrorResponse(404, "Endpoint not found");
            return new Gson().toJson(response, response.getClass());
        });

        Spark.exception(Exception.class, (e, request, response) -> {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw, true);
                e.printStackTrace(pw);
                System.err.println(sw.getBuffer().toString());
                response.type("application/json");
                response.body(sw.getBuffer().toString());
        });
        
    }
    
    public static String checkToken(String token) {
        
        Broker broker = Broker.getInstance();

        if (token == null || !broker.validToken(token)) {
            Response response = new ErrorResponse(401, "Unauthorized");
            return new Gson().toJson(response, response.getClass());
        }
        
        return "";
    }
}

