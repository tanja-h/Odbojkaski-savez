/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.City;
import domain.Club;
import domain.Employee;
import domain.Player;
import domain.Position;
import domain.Tournament;
import form.FormMain;
import form.FormLogin;
import form.club.PanelSearchClub;
import java.awt.Window;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author TANJA-PC
 */
public class ControlerC {

    private static ControlerC instance;

    private FormLogin formLogin;
    private FormMain formMain;
    private Window activeWindow;

    private ControlerC() {
    }

    public static ControlerC getInstance() {
        if (instance == null) {
            instance = new ControlerC();
        }
        return instance;
    }

    public FormLogin getFormLogin() {
        return formLogin;
    }

    public void setFormLogin(FormLogin formLogin) {
        this.formLogin = formLogin;
    }

    public FormMain getFormMain() {
        return formMain;
    }

    public void setFormMain(FormMain formMain) {
        this.formMain = formMain;
    }

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setActiveWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }

    public void loginEmployee(Employee employee) {
        Session.getInstance().setLoggedEmployee(employee);
        formLogin.loginSuccessful();
        formMain = new FormMain();
        formMain.setEmployeeName(employee);
        formMain.setVisible(true);
        formMain.setLocationRelativeTo(null);
        formLogin.setVisible(false);
    }

    public void loginError(Exception e) {
        formLogin.loginError(e.getMessage());
    }

    /*
    public void logoutEmployee() {
        loggedEmployee = null;
        formMain.logoutEmployee();
        formLogin.setVisible(true);
        formMain.dispose();
    }

    public void logout(Exception error) {
        loggedEmployee = null;
        if(formMain != null){
            formMain.logoutAll(error);
            formMain.dispose();
        }
        formLogin.setVisible(true);
    }
     */
    public void logoutEmployee(boolean successful) {
        if (formMain != null) {
            formMain.logout(successful);
            formMain.dispose();
            if (activeWindow != null) {
                activeWindow.dispose();
            }
        }
        formLogin.setVisible(true);
        formLogin.setBtnLoginEnabled();
        System.out.println("[K] odjavljen!");
        Session.getInstance().setLoggedEmployee(null);
    }

    public void populateCityCMBError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(), "Greška!",
                JOptionPane.ERROR_MESSAGE);
    }

    public void clubAdded(String status) {
        formMain.requestClubsForTable();
        
        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                JOptionPane.INFORMATION_MESSAGE);

        int result = JOptionPane.showConfirmDialog(activeWindow, "Nastaviti unos?", "Pitanje",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            formMain.getPanelClub().resetForNewClub();
        } else {
            activeWindow.dispose();
            ControlerC.getInstance().setActiveWindow(formMain);
            ControlerC.getInstance().getFormMain().setPanelClub(null);
        }
    }

    public void clubAddedError(String status) {
        JOptionPane.showMessageDialog(activeWindow,
                "Sistem ne može da registruje klub - " + status,
                "Greška", JOptionPane.ERROR_MESSAGE);
    }
    
    public void setTblClubsError(String status) {
        JOptionPane.showMessageDialog(activeWindow, status);
    }

    public void refreshTblSearchClubs(LinkedList<Club> clubs) {
        formMain.getPanelSearchClub().setListForModelSearchClub(clubs);
    }

    public void clubUpdated(String status) {
        if (status.equals("Podaci o klubu su izmenjeni.")) {
            Club c = (Club) Session.getInstance().getUseCaseParams().get("club");

            PanelSearchClub panel = formMain.getPanelSearchClub();
            panel.getModelSearchClub().updateClub(c);
            formMain.requestClubsForTable();

            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            status = "Sistem ne može da izmeni podatke o klubu: " + status;
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //except for details/view mode
    public void getAllCities(LinkedList<City> cities) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request city");
        switch (request) {
            case "PanelClub":
                formMain.getPanelClub().populateCMBCities(cities);
                break;
            case "PanelSearchClub":
                formMain.getPanelSearchClub().populateCMBCities(cities);
                break;
            case "PanelPlayer":
                formMain.getPanelPlayer().populateCMBCities(cities);
                break;
            case "PanelSearchPlayer":
                formMain.getPanelSearchPlayer().populateCMBCities(cities);
                break;
            case "PanelNewTournament":
                formMain.getPanelNewTournament().populateCMBCities(cities);
                break;    
        }
    }

    public void getAllClubs(LinkedList<Club> clubs) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request club");
        switch (request) {
            case "FormMain":
                formMain.getModelClub().setClubs(clubs);
                break;
            case "PanelSearchClub":
                refreshTblSearchClubs(clubs);
                break;
            case "PanelSearchPlayer":
                formMain.getPanelSearchPlayer().populateCMBClubs(clubs);
                break;
            case "PanelPlayer":
                formMain.getPanelPlayer().populateCMBClubs(clubs);
                break;
            case "PanelNewTournament":
                formMain.getPanelNewTournament().populateCMBClubs(clubs);
                break;     
        }
    }

    public void getAllPositions(LinkedList<Position> positions) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request position");
        switch (request) {
            case "PanelSearchPlayer":
                formMain.getPanelSearchPlayer().populateCMBPositions(positions);
                break;
            case "PanelPlayer":
                formMain.getPanelPlayer().populateCMBPosition(positions);
                break;
        }
    }
    
    public void getAllTournaments(LinkedList<Tournament> tournaments) {
        formMain.getPanelTournaments().getModelTournament().setTournaments(tournaments);
    }
    
    public void getAllCitiesError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(),
                "Greška u zahtevu za gradove!", JOptionPane.ERROR_MESSAGE);
    }

    public void cityAdded(LinkedList<City> cities, String status) {
        formMain.getPanelPlayer().populateCMBCities(cities);

        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void cityAddedError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(), "Greška",
                JOptionPane.ERROR_MESSAGE);
    }

    public void playerAdded(String status) {
        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                JOptionPane.INFORMATION_MESSAGE);

        int result = JOptionPane.showConfirmDialog(activeWindow, "Nastaviti unos?", "Pitanje",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            formMain.getPanelPlayer().resetForNewPlayer();
        } else {
            activeWindow.dispose();
            ControlerC.getInstance().setActiveWindow(formMain);
            ControlerC.getInstance().getFormMain().setPanelClub(null);
        }
    }

    public void playerAddedError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow,
                "Sistem ne može da registruje igrača - " + error.getMessage(), "Greška",
                JOptionPane.ERROR_MESSAGE);
    }

    public void getAllPositionsError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(),
                "Greška u zahtevu za pozicije!", JOptionPane.ERROR_MESSAGE);
    }

    public void setTblSearchPlayers(LinkedList<Player> players) {
        formMain.getPanelSearchPlayer().setListForModelSearchPlayer(players);
    }

    public void setTblPlayersError(String error) {
        JOptionPane.showMessageDialog(activeWindow, error);
    }

    public void playerUpdated(String status, LinkedList<Player> players) {
        Player p = (Player) Session.getInstance().getUseCaseParams().get("player");
        formMain.getPanelSearchPlayer().getModelSearchPlayer().updatePlayer(p);
        //formMain.getModelPlayer().updatePlayer(p);

        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void playerUpdatedError(String status) {
        status = "Sistem ne može da izmeni podatke o igraču: " + status;
        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                JOptionPane.ERROR_MESSAGE);
    }

    public void playerDeleted(String status) {
        if (status.equals("Igrač je uspešno obrisan!")) {
            Player p = (Player) Session.getInstance().getUseCaseParams().get("player");
            formMain.getPanelSearchPlayer().getModelSearchPlayer().removePlayer(p);
            //formMain.requestPlayersForTable();

            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
            formMain.getPanelPlayer().cancel();
        } else {
            status = "Sistem ne može da obriše igrača: " + status;
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tournamentAdded(String status) {
        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
        formMain.getPanelTournaments().requestTournaments();
        
        int result = JOptionPane.showConfirmDialog(activeWindow, "Nastaviti unos?", "Pitanje",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            formMain.getPanelNewTournament().resetForNewT();
        } else {
            formMain.getPanelNewTournament().cancel();
        }
    }

    public void tournamentAddedError(String message) {
        JOptionPane.showMessageDialog(activeWindow,
                "Sistem ne može da zapamti turnir: \n" + message, "Greška",
                JOptionPane.ERROR_MESSAGE);
    }

    

    public void getAllTournamentsError(String message) {
        JOptionPane.showMessageDialog(activeWindow, message, "Greška",
                JOptionPane.ERROR_MESSAGE);
    }

    
    
}
