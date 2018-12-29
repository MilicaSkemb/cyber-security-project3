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
public class BadInputResponse extends ErrorResponse {

    public BadInputResponse() {
        super(400, "Bad Input Provided");
    }
    public BadInputResponse(String message) {
        super(400, message);
    }

}
