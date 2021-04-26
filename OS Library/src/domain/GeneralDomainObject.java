/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author TANJA-PC
 */
public interface GeneralDomainObject extends Serializable{
    public String getTableName();
    public String getJoinClause();
    public String getOrderByClause();
    public String getAttributesNames();
    public String getAttributeValues();
    public String getPrimaryKeyClause();
    public String getUpdateValues();
    
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs);
}


