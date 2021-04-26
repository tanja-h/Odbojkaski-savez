/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author TANJA-PC
 */
public class Tournament implements GeneralDomainObject{
    private int tournamentID;
    private String tournamentName;
    private Date date;
    private Club winner;
    private City city;
    private LinkedList<Match> matches;

    public Tournament() {
    }

    public Tournament(int tournamentID, String tournamentName, Date date, Club winner, City city, LinkedList<Match> matches) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.date = date;
        this.winner = winner;
        this.city = city;
        this.matches = matches;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Club getWinner() {
        return winner;
    }

    public void setWinner(Club winner) {
        this.winner = winner;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }

    public void setMatches(LinkedList<Match> matches) {
        this.matches = matches;
    }
    
    @Override
    public String toString() {
        return tournamentName;
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
        final Tournament other = (Tournament) obj;
        if (this.tournamentID != other.tournamentID) {
            return false;
        }
        return true;
    }

    public LinkedList<Club> getParticipants(){
        LinkedList<Club> list = new LinkedList<>();
        for (Match m : matches) {
            Club host = m.getHost();
            if (!list.contains(host)) {
                list.add(host);
            }
            Club guest = m.getGuest();
            if (!list.contains(guest)) {
                list.add(guest);
            }
        }
        return list;
    }
    
    
    @Override
    public String getTableName() {
        return "tournament";
    }

    @Override
    public String getJoinClause() {
        return " t join city cityt on t.city=cityt.zip_code"
                + " join club cl on t.winner=cl.club_registration_number"
                + " join city cityc on cl.city=cityc.zip_code";
    }

    @Override
    public String getOrderByClause() {
        return "t.date, t.tournament_name";
    }

    @Override
    public String getAttributesNames() {
        return "tournamentID, tournament_name, date, winner, city";
    }

    @Override
    public String getAttributeValues() {
        java.sql.Date dateDB = new java.sql.Date(date.getTime());
        
        return("" + tournamentID + ", '" + tournamentName
                + "', '" + dateDB + "', '" + winner.getClubRegistrationNumber()
                + "', '" + city.getZipCode() + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "tournamentID = " + tournamentID + "";
    }

    @Override
    public String getUpdateValues() {
        //tournamentID can't be changed
        java.sql.Date dateDB = new java.sql.Date(date.getTime());
        
        return "tournament_name = '" + tournamentName + "',"
            + " date = '" + dateDB + "',"
            + " winner = '" + winner.getClubRegistrationNumber() + "',"
            + " city = '" + city.getZipCode() + "'";
    }

    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Tournament t = new Tournament();
                t.setTournamentID(rs.getInt("t.tournamentID"));
                t.setTournamentName(rs.getString("t.tournament_name"));
                
                Date d = new Date(rs.getDate("t.date").getTime());
                t.setDate(d);
                
                City cityt = new City();
                cityt.setZipCode(rs.getString("cityt.zip_code"));
                cityt.setCityName(rs.getString("cityt.city_name"));
                t.setCity(cityt);

                Club winner1 = new Club();
                winner1.setClubRegistrationNumber(rs.getString("cl.club_registration_number"));
                winner1.setClubName(rs.getString("cl.club_name"));
                winner1.setClubAdress(rs.getString("cl.club_adress"));
                winner1.setNumberOfWins_Match(rs.getInt("cl.number_of_wins_match"));
                winner1.setNumberOfWins_Tournament(rs.getInt("cl.number_of_wins_tournament"));
                City cityc = new City();
                cityc.setZipCode(rs.getString("cityc.zip_code"));
                cityc.setCityName(rs.getString("cityc.city_name"));
                winner1.setCity(cityc);
                t.setWinner(winner1);
                
                t.setMatches(new LinkedList<>());
                
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("sql greska kod tournament-getObjectList");
        } catch (Exception ex) {
            System.out.println("greska kod tournament-getObjectList" + ex.getMessage());
        }
        return list;
    }

    
    
}
