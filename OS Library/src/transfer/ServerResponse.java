/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author TANJA-PC
 */
public class ServerResponse implements Serializable{
    private int operation;
    private Object data;
    private boolean successful;
    private Exception error;
    //private String message;

    public ServerResponse() {
    }

    public ServerResponse(int operation, Object data, boolean successful, Exception error) {
        this.operation = operation;
        this.data = data;
        this.successful = successful;
        this.error = error;
    }

    

    
    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }
    
    
}
