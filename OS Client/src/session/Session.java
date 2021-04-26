/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Employee;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TANJA-PC
 */
public class Session {
    private static Session instance;
    
    private Employee loggedEmployee;
    private final Map<String, Object> useCaseParams;


    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() {
        useCaseParams = new HashMap<>();
    }

    public Employee getLoggedEmployee() {
        return loggedEmployee;
    }

    public void setLoggedEmployee(Employee loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }

    public Map<String, Object> getUseCaseParams() {
        return useCaseParams;
    }
    
    
}
