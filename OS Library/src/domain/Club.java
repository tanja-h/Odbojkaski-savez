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
public class Club implements GeneralDomainObject{
    private String clubRegistrationNumber;
    private String clubName;
    private String clubAdress;
    private int numberOfWins_Match;
    private int numberOfWins_Tournament;
    private City city;
    //nema listu igraca -> igrac ima klub

    public Club() {
    }

    public Club(String clubRegistrationNumber, String clubName, String clubAdress, int numberOfWins_Match, int numberOfWins_Tournament, City city) {
        this.clubRegistrationNumber = clubRegistrationNumber;
        this.clubName = clubName;
        this.clubAdress = clubAdress;
        this.numberOfWins_Match = numberOfWins_Match;
        this.numberOfWins_Tournament = numberOfWins_Tournament;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getClubRegistrationNumber() {
        return clubRegistrationNumber;
    }

    public void setClubRegistrationNumber(String clubRegistrationNumber) {
        this.clubRegistrationNumber = clubRegistrationNumber;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubAdress() {
        return clubAdress;
    }

    public void setClubAdress(String clubAdress) {
        this.clubAdress = clubAdress;
    }

    public int getNumberOfWins_Match() {
        return numberOfWins_Match;
    }

    public void setNumberOfWins_Match(int numberOfWins_Match) {
        this.numberOfWins_Match = numberOfWins_Match;
    }

    public int getNumberOfWins_Tournament() {
        return numberOfWins_Tournament;
    }

    public void setNumberOfWins_Tournament(int numberOfWins_Tournament) {
        this.numberOfWins_Tournament = numberOfWins_Tournament;
    }

        
    
    @Override
    public String toString() {
        return clubName;
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
        final Club other = (Club) obj;
        if (!Objects.equals(this.clubRegistrationNumber, other.clubRegistrationNumber)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String getTableName() {
        return "club";
    }

    @Override
    public String getJoinClause() {
        return " cl join city c on cl.city=c.zip_code ";
    }

    @Override
    public String getOrderByClause() {
        return "cl.city, cl.club_name";
    }
    
    @Override
    public String getAttributesNames() {
         return "club_registration_number, club_name, club_adress, "
              + "number_of_wins_match, number_of_wins_tournament, city";
    }

    
    
    @Override
    public String getAttributeValues() {
        return("'" + clubRegistrationNumber + "', '" + clubName + "', '" + clubAdress
                + "', " + numberOfWins_Match + ", " + numberOfWins_Tournament
                + ", '" + city.getZipCode() + "'");
    }
    
    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()){
                Club club = new Club();
                club.setClubRegistrationNumber(rs.getString("cl.club_registration_number"));
                club.setClubName(rs.getString("cl.club_name"));
                club.setClubAdress(rs.getString("cl.club_adress"));
                club.setNumberOfWins_Match(rs.getInt("cl.number_of_wins_match"));
                club.setNumberOfWins_Tournament(rs.getInt("cl.number_of_wins_tournament"));
                    City cityc = new City();
                    cityc.setZipCode(rs.getString("c.zip_code"));
                    cityc.setCityName(rs.getString("c.city_name"));
                club.setCity(cityc);
                
                list.add(club);
            }
        } catch (SQLException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("greska kod club");
        }
        return list;
    }

    @Override
    public String getPrimaryKeyClause() {
        return "club_registration_number = '" + clubRegistrationNumber + "'";
    }

    
    @Override
    public String getUpdateValues() {
        //club_registration_number can't be changed
        return " club_name = '" + clubName + "',"
             + " club_adress = '" + clubAdress + "',"
             + " city = '" + city.getZipCode() + "',"
             + " number_of_wins_match = '" + numberOfWins_Match + "',"
             + " number_of_wins_tournament = '" + numberOfWins_Tournament + "'";
    }

    

}
