/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.tournament.match.model;

import domain.Club;
import domain.Match;
import domain.domainEnum.Winner;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import logic.ControlerC;
import util.DateFormatter;

/**
 *
 * @author TANJA-PC
 */
public class TableModelMatch extends AbstractTableModel {

    private LinkedList<Match> matches;
    private String[] header = new String[]{"Turnir ID", "Utakmica ID", "Domaćin", "Gost",
        "Rezultat", "Pobednik", "Vreme"};

    public TableModelMatch(LinkedList<Match> matches) {
        this.matches = matches;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }

    public void setMatches(LinkedList<Match> matches) {
        this.matches = matches;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return matches.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Match getMatch(int rowIndex) {
        return matches.get(rowIndex);
    }

    public void addMatch(Match m) {
        matches.add(m);
        fireTableDataChanged();
    }

    public void removeMatch(int rowIndex) {
        matches.remove(rowIndex);
        fireTableDataChanged();
    }
    
    public void removeMatches() {
        matches.clear();
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Match m = matches.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getTournamentID();
            case 1:
                return m.getMatchID();
            case 2:
                return m.getHost();
            case 3:
                return m.getGuest();
            case 4:
                return m.getScore();
            case 5:
                return m.getWinner();
            case 6:
                if (m.getTime() != null) {
                    return DateFormatter.timeToStringApp(m.getTime());
                } else {
                    return "";
                }
            default:
                return "n/a";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Match m = matches.get(rowIndex);
        switch (columnIndex) {
            case 0:
                m.setTournamentID(Integer.valueOf(aValue.toString()));
                break;
            case 1:
                m.setMatchID(Integer.valueOf(aValue.toString()));
                break;
            case 2:
                if(m.getGuest() == null || m.getGuest() != ((Club) aValue)){
                    m.setHost((Club) aValue);
                }else{
                    JOptionPane.showMessageDialog(ControlerC.getInstance().getActiveWindow(), 
                            "Domaćin i gost moraju biti različiti");
                }
                break;
            case 3:
                if(m.getHost()== null || m.getHost()!= ((Club) aValue)){
                    m.setGuest((Club) aValue);
                }else{
                    JOptionPane.showMessageDialog(ControlerC.getInstance().getActiveWindow(), 
                            "Domaćin i gost moraju biti različiti");
                }
                break;
            case 4:
                try {
                    validateScore(aValue.toString().trim());
                    m.setScore(aValue.toString().trim());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ControlerC.getInstance().getActiveWindow(), ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case 5:
                m.setWinner((Winner) aValue);
                break;
            case 6:
                try {
                    if (!("".equals(aValue.toString()))) {
                        m.setTime(DateFormatter.stringToTimeApp(aValue.toString()));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ControlerC.getInstance().getActiveWindow(), ex.getMessage());
                }
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

    private void validateScore(String score) throws Exception {
        if (score != null && !score.equals("")) {
            if (score.length() != 3) {
                throw new Exception("Neispravan format rezultata!");
            }
            
            //if(Character.compare(score.charAt(1),':') != 0){
            if (!score.substring(1,2).equals(":")) {
                throw new Exception("Neispravan format rezultata! (D:G)");
            }
            String[] res = score.split(":");
            if (res.length != 2) {
                throw new Exception("Neispravan format rezultata! (D:G)");
            }
 
            if(!Character.isDigit(res[0].charAt(0)) || !Character.isDigit(res[1].charAt(0))){
                throw new Exception("Neispravan format rezultata! (slova)");
            }
 
            if(Integer.valueOf(res[0])<0 || Integer.valueOf(res[0])>3 ||
                    Integer.valueOf(res[1])<0 || Integer.valueOf(res[1])>3){
                throw new Exception("Broj dobijenih setova mora biti broj od 0 do 3!");
            }
            if(Integer.valueOf(res[0])!=3 && Integer.valueOf(res[1])!=3){
                throw new Exception("Pobednik mora da dobije 3 seta!");
            }
            if(Integer.valueOf(res[0])==3 && Integer.valueOf(res[1])==3){
                throw new Exception("Rezultat utakmice ne može biti izjednačen!");
            }
            
            
        }
    }

}
