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
public class City implements GeneralDomainObject{
    private String zipCode;
    private String cityName;

    public City() {
    }

    public City(String zipCode, String cityName) {
        this.zipCode = zipCode;
        this.cityName = cityName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return cityName;
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
        final City other = (City) obj;
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        return true;
    }

        
    
    @Override
    public String getTableName() {
        return "city";
    }

    @Override
    public String getJoinClause() {
        return "";
    }
    
    @Override
    public String getOrderByClause() {
        return "city_name";
    }

    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()){
                City c = new City();
                c.setZipCode(rs.getString("zip_code"));
                c.setCityName(rs.getString("city_name"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("greska kod grada");
        }
        return list;
    }

    @Override
    public String getAttributesNames() {
        return "zip_code, city_name";
    }

    
    
    @Override
    public String getAttributeValues() {
        return("'" + zipCode + "', '" + cityName + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "zip_code = '" + zipCode + "'";
    }

    @Override
    public String getUpdateValues() {
        return "zip_code = '" + zipCode + "',"
             + " city_name = '" + cityName + "'";
    }
    
    
    
}
