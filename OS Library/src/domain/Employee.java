/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TANJA-PC
 */
public class Employee implements GeneralDomainObject{
    private String username;
    private String password;
    private String name;

    public Employee() {
    }

    public Employee(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Sluzbenik{" + "korisnickoIme=" + username + ", lozinka=" + password + ", imePrezime=" + name + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "employee";
    }

    @Override
    public String getJoinClause() {
        return "";
    }

    @Override
    public String getOrderByClause() {
        return "name";
    }

    
    @Override
    public String getAttributesNames() {
        return "ussername, password, name";
    }

    
    @Override
    public String getAttributeValues() {
        return("'" + username + "', '" + password + "', '" + name + "'");
    }
    
    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()){
                Employee e = new Employee();
                e.setUsername(rs.getString("username"));
                e.setPassword(rs.getString("password"));
                e.setName(rs.getString("name"));
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("greska kod employee");
        }
        return list;
    }

    @Override
    public String getPrimaryKeyClause() {
        return "username = '" + username + "'";
    }

    @Override
    public String getUpdateValues() {
        return "username = '" + username + "',"
             + " password = '" + password + "',"
             + " name = '" + name + "'";
    }

    
}
