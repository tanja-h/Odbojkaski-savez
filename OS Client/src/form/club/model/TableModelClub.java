/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.club.model;

import domain.City;
import domain.Club;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TANJA-PC
 */
public class TableModelClub extends AbstractTableModel {

    private LinkedList<Club> clubs;
    private String[] header = new String[]{"Reg. broj", "Naziv", "Grad",
        "Adresa", "Pobede-utakmice", "Pobede-turniri"};

    public TableModelClub(LinkedList<Club> clubs) {
        this.clubs = clubs;
    }

    public LinkedList<Club> getClubs() {
        return clubs;
    }

    public void setClubs(LinkedList<Club> clubs) {
        this.clubs = clubs;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return clubs.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Club getClub(int rowIndex) {
        return clubs.get(rowIndex);
    }

    public void addClub(Club c) {
        clubs.add(c);
        fireTableDataChanged();
    }

    public void removeClub(int rowIndex) {
        clubs.remove(rowIndex);
        fireTableDataChanged();
    }
    
    public void removeClubs() {
        clubs.clear();
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Club c = clubs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getClubRegistrationNumber();
            case 1:
                return c.getClubName();
            case 2:
                return c.getCity();
            case 3:
                return c.getClubAdress();
            case 4:
                return c.getNumberOfWins_Match();
            case 5:
                return c.getNumberOfWins_Tournament();
            default:
                return "n/a";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Club c = clubs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                c.setClubRegistrationNumber(aValue.toString());
                break;
            case 1:
                c.setClubName(aValue.toString());
                break;
            case 2:
                c.setCity((City) aValue);
                break;
            case 3:
                c.setClubAdress(aValue.toString());
                break;
            case 4:
                c.setNumberOfWins_Match(Integer.valueOf(aValue.toString()));
                break;
            case 5:
                c.setNumberOfWins_Tournament((int) aValue);
                break;

        }
    }

    public void updateClub(Club c) {
        for (Club club : clubs) {
            if (club.equals(c)) {
                //ne sme da se menja reg.broj
                club.setClubName(c.getClubName());
                club.setClubAdress(c.getClubAdress());
                club.setCity(c.getCity());
                club.setNumberOfWins_Match(c.getNumberOfWins_Match());
                club.setNumberOfWins_Tournament(c.getNumberOfWins_Tournament());
                fireTableDataChanged();
            }
        }
    }

    
}
