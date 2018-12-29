/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.database;

import fi.secure.owasp.project.domen.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author milicas
 */
public class Broker {
    
    private static Broker instance = null;
    private Connection connection;
    
    public static Broker getInstance() {
        if (instance == null)
            instance = new Broker();
        return instance;
    }

    public Broker() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY, "
                    + "username TEXT, "
                    + "password TEXT NOT NULL, "
                    + "email text NOT NULL, "
                    + "token text NOT NULL, "
                    + "about text NOT NULL, "
                    + "site text NOT NULL, "
                    + "created_at text NOT NULL"
                    + ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS posts ("
                    + "id INTEGER PRIMARY KEY, "
                    + "user_id INTEGER, "
                    + "title TEXT, "
                    + "content TEXT NOT NULL, "
                    + "created_at text NOT NULL"
                    + ");");
            
        } catch(SQLException e) {           
            System.err.println(e.getMessage());
        }
        
    }
    
    public boolean validToken(String token) {
        
        try {
            Statement statement = this.connection.createStatement();          

            ResultSet rs = statement.executeQuery("select id from users where token = '"+token+"' order by id desc limit 1");
            while (rs.next()){
                return true;
            }
            
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public boolean usernameAvailable(String username) {
        
        try {
            Statement statement = this.connection.createStatement();          

            ResultSet rs = statement.executeQuery("select id from users where username = '"+username+"' order by id desc limit 1");
            while (rs.next()){
                return false;
            }
            
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
    
    public User loginUser(String username, String password) {
        User user = null;        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {
            Statement statement = this.connection.createStatement();          

            ResultSet rs = statement.executeQuery("select * from users where username = '"+username+"' and password = '"+password+"' order by id desc");
            while (rs.next()){
                user = new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("token"),
                    rs.getString("about"),
                    rs.getString("site"),
                    sdf.parse(rs.getString("created_at"))
                );
            }
            
        } catch(SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }
    
     public User getUserByID(long id) {
        User user = null;        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {
            Statement statement = this.connection.createStatement();          

            ResultSet rs = statement.executeQuery("select * from users where id = '"+id+"' order by id desc");
            while (rs.next()){
                user = new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("token"),
                    rs.getString("about"),
                    rs.getString("site"),
                    sdf.parse(rs.getString("created_at"))
                );
            }
            
        } catch(SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }
    
    public long addUser(User user) {        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created_at = sdf.format(user.getCreated_at());
        
        try {
            Statement statement = this.connection.createStatement();          
            statement.executeUpdate("insert into users (username, password, email, token, about, site, created_at) "
                    + "values("
                    + "'" + user.getUsername() + "'" +"," 
                    + "'" +user.getPassword() + "'" +"," 
                    + "'" +user.getEmail() + "'" + "," 
                    + "'" +user.getToken() + "'" + "," 
                    + "'" +user.getAbout() + "'" + "," 
                    + "'" +user.getSite() + "'" + "," 
                    + "'" +created_at +"'"
                    + ")");
            
            ResultSet rs = statement.executeQuery("select id from users where token = '"+user.getToken()+"' order by id desc");
            while (rs.next()) {
                return rs.getLong("id");
            }
            
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
    
    
}
