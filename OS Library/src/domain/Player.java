/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.domainEnum.Gender;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author TANJA-PC
 */
public class Player implements GeneralDomainObject {

    private Club club;
    private String playerRegistrationNumber;
    private String name;
    private Gender gender;
    private Date dateOfBirth;
    private String playerAdress;
    private City city;
    private Position position;
    private int jerseyNumber;

    public Player() {
    }

    public Player(Club club, String playerRegistrationNumber, String name, Gender gender, Date dateOfBirth, String playerAdress, City city, Position position, int jerseyNumber) {
        this.club = club;
        this.playerRegistrationNumber = playerRegistrationNumber;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.playerAdress = playerAdress;
        this.city = city;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPlayerRegistrationNumber() {
        return playerRegistrationNumber;
    }

    public void setPlayerRegistrationNumber(String playerRegistrationNumber) {
        this.playerRegistrationNumber = playerRegistrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlayerAdress() {
        return playerAdress;
    }

    public void setPlayerAdress(String playerAdress) {
        this.playerAdress = playerAdress;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.playerRegistrationNumber, other.playerRegistrationNumber)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String getTableName() {
        return "player";
    }

    @Override
    public String getJoinClause() {
        return " p join city cityp on p.city=cityp.zip_code"
                + " join club cl on p.club_registration_number=cl.club_registration_number"
                + " join city cityc on cl.city=cityc.zip_code"
                + " join position pos on p.positionID=pos.positionID";
    }

    @Override
    public String getOrderByClause() {
        return "p.name";
    }

    @Override
    public String getAttributesNames() {
        return "player_registration_number, name, gender, date_of_birth, player_adress, "
                +"jersey_number, city, positionID, club_registration_number";
    }
    
    
    @Override
    public String getAttributeValues() {
        java.sql.Date dateOfBirthDB = new java.sql.Date(dateOfBirth.getTime());
        
        return("'" + playerRegistrationNumber + "', '" + name + "', '" + gender.toString()
                + "', '" + dateOfBirthDB + "', '" + playerAdress + "', " + jerseyNumber + ", '" + city.getZipCode()
                + "', " + position.getPositionID() + ", '" + club.getClubRegistrationNumber() + "'");
    }
    
    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Player p = new Player();
                p.setPlayerRegistrationNumber(rs.getString("p.player_registration_number"));
                p.setName(rs.getString("p.name"));
                p.setGender(Gender.valueOf(rs.getString("p.gender")));
                
                Date dateofbirth = new Date(rs.getDate("p.date_of_birth").getTime());
                p.setDateOfBirth(dateofbirth);
                
                p.setPlayerAdress(rs.getString("p.player_adress"));
                p.setJerseyNumber(rs.getInt("p.jersey_number"));
                
                Position pos = new Position();
                pos.setPositionID(rs.getInt("pos.positionID"));
                pos.setPositionName(rs.getString("pos.position_name"));
                p.setPosition(pos);
                
                City cityp = new City();
                cityp.setZipCode(rs.getString("cityp.zip_code"));
                cityp.setCityName(rs.getString("cityp.city_name"));
                p.setCity(cityp);

                Club cl = new Club();
                cl.setClubRegistrationNumber(rs.getString("cl.club_registration_number"));
                cl.setClubName(rs.getString("cl.club_name"));
                cl.setClubAdress(rs.getString("cl.club_adress"));
                cl.setNumberOfWins_Match(rs.getInt("cl.number_of_wins_match"));
                cl.setNumberOfWins_Tournament(rs.getInt("cl.number_of_wins_tournament"));
                City cityc = new City();
                cityc.setZipCode(rs.getString("cityc.zip_code"));
                cityc.setCityName(rs.getString("cityc.city_name"));
                cl.setCity(cityc);
                
                p.setClub(cl);
                
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("greska kod player-getObjectList");
        } catch (Exception ex) {
            System.out.println("greska kod player-getObjectList" + ex.getMessage());
        }
        return list;
    }

    @Override
    public String getPrimaryKeyClause() {
        return "player_registration_number = '" + playerRegistrationNumber + "'";
    }

    @Override
    public String getUpdateValues() {
        //player_registration_number can't be changed
        java.sql.Date dateOfBirthDB = new java.sql.Date(dateOfBirth.getTime());
        return "name = '" + name + "',"
            + " gender = '" + gender.toString() + "',"
            + " date_of_birth = '" + dateOfBirthDB + "',"
            + " player_adress = '" + playerAdress + "',"
            + " jersey_number = " + jerseyNumber + ","
            + " positionID = " + position.getPositionID() + ","
            + " city = '" + city.getZipCode() + "',"
            + " club_registration_number = '" + club.getClubRegistrationNumber() + "'";
    }

    
    
}
