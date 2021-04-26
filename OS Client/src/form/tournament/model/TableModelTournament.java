/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.tournament.model;

import domain.Tournament;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import util.DateFormatter;

/**
 *
 * @author TANJA-PC
 */
public class TableModelTournament extends AbstractTableModel {

    private LinkedList<Tournament> tournaments;
    private String[] header = new String[]{"TurnirID", "Naziv turnira", "Datum",
        "Grad", "Pobednik"};

    public TableModelTournament(LinkedList<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public LinkedList<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(LinkedList<Tournament> tournaments) {
        this.tournaments = tournaments;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return tournaments.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Tournament getTournament(int rowIndex) {
        return tournaments.get(rowIndex);
    }

    public void addTournament(Tournament t) {
        tournaments.add(t);
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tournament t = tournaments.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getTournamentID();
            case 1:
                return t.getTournamentName();
            case 2:
                return DateFormatter.dateToStringApp(t.getDate());
            case 3:
                return t.getCity();
            case 4:
                return t.getWinner();
            default:
                return "n/a";
        }
    }

    /*
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tournament t = tournaments.get(rowIndex);
        switch (columnIndex) {
            case 0:
                t.setTournamentID(Integer.valueOf(aValue.toString()));
                break;
            case 1:
                t.setTournamentName(aValue.toString());
                break;
            case 2: {
                try {
                    t.setDate(DateFormatter.stringToDateApp(aValue.toString()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ControlerC.getInstance().getActiveWindow(), ex.getMessage());
                }
            }
                break;
            case 3:
                t.setCity((City) aValue);
                break;
            case 4:
                t.setWinner((Club) aValue);
                break;
        }
    }
    */
    
}
