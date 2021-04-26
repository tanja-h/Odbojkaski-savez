/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TANJA-PC
 */
public class Position implements GeneralDomainObject{
    private int positionID;
    private String positionName;

    public Position() {
    }

    public Position(int positionID, String name) {
        this.positionID = positionID;
        this.positionName = name;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    @Override
    public String toString() {
        return positionName;
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
        final Position other = (Position) obj;
        if (this.positionID != other.positionID) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "position";
    }

    @Override
    public String getJoinClause() {
        return "";
    }

    @Override
    public String getOrderByClause() {
        return "positionID";
    }

    @Override
    public String getAttributesNames() {
        return "positionID, position_name";
    }

    @Override
    public String getAttributeValues() {
        return("" + positionID + ", '" + positionName + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "positionID = " + positionID;
    }

    @Override
    public String getUpdateValues() {
        return "positionID = " + positionID + ","
             + " position_name = '" + positionName + "'";
    }

    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Position p = new Position();
                p.setPositionID(rs.getInt("positionID"));
                p.setPositionName(rs.getString("position_name"));
                
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("greska kod position-getObjectList");
        }
        return list;
    }

   
    
    
}
