/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.domainEnum.Winner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author TANJA-PC
 */
public class Match implements GeneralDomainObject{
    
    private int tournamentID; 
    private int matchID;
    private Club host;
    private Club guest;
    private String score;
    private Winner winner;
    private Date time;

    public Match() {
    }

    public Match(int tournamentID, int matchID, Club host, Club guest, String score, Winner winner, Date time) {
        this.tournamentID = tournamentID;
        this.matchID = matchID;
        this.host = host;
        this.guest = guest;
        this.score = score;
        this.winner = winner;
        this.time = time;
    }

    

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }
    
    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Club getHost() {
        return host;
    }

    public void setHost(Club host) {
        this.host = host;
    }

    public Club getGuest() {
        return guest;
    }

    public void setGuest(Club guest) {
        this.guest = guest;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return matchID + "";
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
        final Match other = (Match) obj;
        if (this.matchID != other.matchID) {
            return false;
        }
        if (this.tournamentID != other.tournamentID) {
            return false;
        }
        return true;
    }
    
    @Override
    public String getTableName() {
        return "game";
    }

    @Override
    public String getJoinClause() {
        return " m join club host on m.host=host.club_registration_number"
               + " join city cityh on host.city=cityh.zip_code"
               + " join club guest on m.guest=guest.club_registration_number"
               + " join city cityg on guest.city=cityg.zip_code"
               + " join tournament t on m.tournamentID=t.tournamentID";
    }

    @Override
    public String getOrderByClause() {
        return "m.tournamentID desc, m.matchID";
    }

    @Override
    public String getAttributesNames() {
        return "tournamentID, matchID, host, guest, score, winner, time";
    }

    @Override
    public String getAttributeValues() {
        java.sql.Time timeDB = new java.sql.Time(time.getTime());
        
        return("" + tournamentID + ", " + matchID
                + ", '" + host.getClubRegistrationNumber()
                + "', '" + guest.getClubRegistrationNumber()
                + "', '" + score + "', '" + winner.toString()
                + "', '" + timeDB + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "m.matchID = " + matchID + " AND m.tournamentID = " + tournamentID;
    }

    @Override
    public String getUpdateValues() {
        java.sql.Time timeDB = new java.sql.Time(time.getTime());
        
        //matchID and tournamentID can't be changed
        return " score = '" + score + "',"
             + " time = '" + timeDB + "',"
             + " host = '" + host.getClubRegistrationNumber() + "',"
             + " guest = '" + guest.getClubRegistrationNumber() + "'";
    }

    @Override
    public LinkedList<GeneralDomainObject> getObjectList(ResultSet rs) {
        LinkedList<GeneralDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Match m = new Match();
                m.setMatchID(rs.getInt("m.matchID"));
                m.setTournamentID(rs.getInt("m.tournamentID"));
                m.setTime(new Date(rs.getTime("m.time").getTime()));
                m.setScore(rs.getString("m.score"));
                m.setWinner(Winner.valueOf(rs.getString("m.winner")));
                
                Club clHost = new Club();
                clHost.setClubRegistrationNumber(rs.getString("host.club_registration_number"));
                clHost.setClubName(rs.getString("host.club_name"));
                clHost.setClubAdress(rs.getString("host.club_adress"));
                clHost.setNumberOfWins_Match(rs.getInt("host.number_of_wins_match"));
                clHost.setNumberOfWins_Tournament(rs.getInt("host.number_of_wins_tournament"));
                    City cityh = new City();
                    cityh.setZipCode(rs.getString("cityh.zip_code"));
                    cityh.setCityName(rs.getString("cityh.city_name"));
                clHost.setCity(cityh);
                m.setHost(clHost);
                
                Club clGuest = new Club();
                clGuest.setClubRegistrationNumber(rs.getString("guest.club_registration_number"));
                clGuest.setClubName(rs.getString("guest.club_name"));
                clGuest.setClubAdress(rs.getString("guest.club_adress"));
                clGuest.setNumberOfWins_Match(rs.getInt("guest.number_of_wins_match"));
                clGuest.setNumberOfWins_Tournament(rs.getInt("guest.number_of_wins_tournament"));
                    City cityg = new City();
                    cityg.setZipCode(rs.getString("cityg.zip_code"));
                    cityg.setCityName(rs.getString("cityg.city_name"));
                clGuest.setCity(cityg);
                m.setGuest(clGuest);
                
                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println("greska kod match-getObjectList");
        } catch (Exception ex) {
            System.out.println("greska kod match-getObjectList" + ex.getMessage());
        }
        return list;
    }    
    
}
