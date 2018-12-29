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
public abstract class Response {

    protected boolean success;
    protected int code;

    public Response(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }
}
