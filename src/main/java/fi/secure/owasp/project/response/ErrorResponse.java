/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.secure.owasp.project.response;

/**
 *
 * @author milicas
 */
public class ErrorResponse extends Response {

    protected String message;

    public ErrorResponse(int code, String message) {
        super(false, code);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
