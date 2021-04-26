/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.player.model;


import domain.City;
import domain.Club;
import domain.domainEnum.Gender;
import domain.Player;
import domain.Position;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import util.DateFormatter;

/**
 *
 * @author TANJA-PC
 */
public class TableModelPlayer extends AbstractTableModel{
    private LinkedList<Player> players;
    private String[] header = new String[]{"Reg. broj", "Ime i prezime", "pol",
            "Datum rođenja", "Adresa", "Grad", "Pozicija", "Broj dresa", "Klub"};

    public TableModelPlayer(LinkedList<Player> players) {
        this.players = players;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
        fireTableDataChanged();
    }

    
    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return header[columnIndex];
    }
    

    public Player getPlayer(int rowIndex){
        return players.get(rowIndex);
    }
    
    public void addClub(Player p){
        players.add(p);
        fireTableDataChanged();
    }
    
    public void removePlayer(Player p){
        players.remove(p);
        fireTableDataChanged();
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player p = players.get(rowIndex);
        switch (columnIndex){
            case 0: return p.getPlayerRegistrationNumber();
            case 1: return p.getName();
            case 2: return p.getGender().toString();
            case 3: return DateFormatter.dateToStringApp(p.getDateOfBirth());
            case 4: return p.getPlayerAdress();
            case 5: return p.getCity();
            case 6: return p.getPosition();
            case 7: return p.getJerseyNumber();
            case 8: return p.getClub();
            
            default: return "n/a";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Player p = players.get(rowIndex);
        switch (columnIndex) {
            case 0:
                p.setPlayerRegistrationNumber(aValue.toString());
                break;
            case 1:
                p.setName(aValue.toString());
                break;
            case 2:
                p.setGender((Gender) aValue);
                break;
            case 3:
                p.setDateOfBirth((Date)aValue);
                break;
            case 4:
                p.setPlayerAdress(aValue.toString());
                break;
            case 5:
                p.setCity((City)aValue);
                break;
            case 6:
                p.setPosition((Position)(aValue));
                break;
            case 7:
                p.setJerseyNumber((int) aValue);
                break;
            case 8:
                p.setClub((Club) aValue);
                break;
        }
    }

    public void updatePlayer(Player p) {
        for (Player player : players) {
            if (player.equals(p)){
                //ne sme da se menja reg.broj
                player.setName(p.getName());
                player.setGender(p.getGender());
                player.setDateOfBirth(p.getDateOfBirth());
                player.setPlayerAdress(p.getPlayerAdress());
                player.setCity(p.getCity());
                player.setPosition(p.getPosition());
                player.setClub(p.getClub());
                fireTableDataChanged();
            }
        }
    }

    
}
