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
public class ClientRequest implements Serializable{
    private int operation;
    private Object parameter;

    public ClientRequest() {
    }

    public ClientRequest(int operation, Object parameter) {
        this.operation = operation;
        this.parameter = parameter;
    }

    

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }

    public Object getParameter() {
        return parameter;
    }
    
    
}
