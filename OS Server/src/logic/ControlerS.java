/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Operation;
import domain.Employee;
import java.util.LinkedList;
import domain.City;
import domain.Club;
import domain.Player;
import domain.Position;
import domain.Tournament;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import so.city.GetAllCitiesSO;
import so.city.SaveCitySO;
import so.club.GetAllClubsSO;
import so.club.SaveClubSO;
import so.club.SearchClubsSO;
import so.club.UpdateClubSO;
import so.employee.GetAllEmployeesSO;
import so.player.DeletePlayerSO;
import so.player.GetAllPlayersSO;
import so.player.SavePlayerSO;
import so.player.SearchPlayersSO;
import so.player.UpdatePlayerSO;
import so.position.GetAllPositionsSO;
import so.tournament.GetAllTournamentsSO;
import so.tournament.SaveTournamentSO;
import start.ServerStart;
import threads.HandleClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author TANJA-PC
 */
public class ControlerS {

    private static ControlerS instance;
    private ServerStart serverStart;

    private ControlerS() {
    }

    public static ControlerS getInstance() {
        if (instance == null) {
            instance = new ControlerS();
        }
        return instance;
    }

    public void setServerStart(ServerStart serverStart) {
        this.serverStart = serverStart;
    }

    public ServerStart getServerStart() {
        return serverStart;
    }

    public void stopServer() {
        ServerResponse sr = new ServerResponse();
        sr.setOperation(Operation.LOGOUT);
        sr.setSuccessful(false);    //server nasilno prekida komunikaciju
        //sr.setError(new Exception("Veza sa serverom je prekinuta!"));

        for (HandleClientRequest hcr : serverStart.getClientsThreadsList()) {
            try {
                hcr.sendResponse(sr);
                hcr.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(ControlerS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        serverStart.getLoggedEmployees().clear();
        serverStart.stopServer();
    }

    public Employee loginEmployee(Employee requestedEmployee) throws Exception {
        if (serverStart.getLoggedEmployees().contains(requestedEmployee)) {
            throw new Exception("Korisnik je već prijavljen!");
        }
        
        AbstractGenericOperation so = new GetAllEmployeesSO();
        so.templateExecute(requestedEmployee);
        LinkedList<Employee> employees = ((GetAllEmployeesSO) so).getList();

        for (Employee e : employees) {
            if (e.getUsername().equals(requestedEmployee.getUsername())) {
                if (e.getPassword().equals(requestedEmployee.getPassword())) {
                    serverStart.getLoggedEmployees().add(e);
                    System.out.println("[login] lista employee na serverStart: ");
                    System.out.println(serverStart.getLoggedEmployees());
                    return e;
                } else {
                    throw new Exception("Pogrešna lozinka");
                }
            }
        }

        throw new Exception("Korisnik sa unetim korisničkim imenom ne postoji!");
    }

    public void logoutEmployee(Employee e) {
        //System.out.println(e + " je odjavljen!");
        serverStart.getLoggedEmployees().remove(e);
        System.out.println("[logout] lista employee na serverStart: ");
        System.out.println(serverStart.getLoggedEmployees());
    }

    
    public LinkedList<City> getAllCities() throws Exception {
        AbstractGenericOperation so = new GetAllCitiesSO();
        so.templateExecute(new City());
        return ((GetAllCitiesSO) so).getList();
    }

    public void addClub(Club club) throws Exception {
        AbstractGenericOperation so = new SaveClubSO();
        so.templateExecute(club);
    }

    public LinkedList<Club> getAllClubs() throws Exception {
        AbstractGenericOperation so = new GetAllClubsSO();
        so.templateExecute(new Club());
        return ((GetAllClubsSO) so).getList();
    }

    public void updateClub(Club cl) throws Exception {
        AbstractGenericOperation so = new UpdateClubSO();
        so.templateExecute(cl);
    }

    //search by criterion
    public LinkedList<Club> searchClubs(String criterion) throws Exception {
        AbstractGenericOperation so = new SearchClubsSO(criterion);
        so.templateExecute(new Club());
        return ((SearchClubsSO) so).getList();
    }

    public void addCity(City city) throws Exception {
        AbstractGenericOperation so = new SaveCitySO();
        so.templateExecute(city);
    }

    public void addPlayer(Player player) throws Exception {
        AbstractGenericOperation so = new SavePlayerSO();
        so.templateExecute(player);
    }

    public LinkedList<Player> searchPlayers(String criterion) throws Exception {
        AbstractGenericOperation so = new SearchPlayersSO(criterion);
        so.templateExecute(new Player());
        return ((SearchPlayersSO) so).getList();
    }

    public LinkedList<Position> getAllPositions() throws Exception {
        AbstractGenericOperation so = new GetAllPositionsSO();
        so.templateExecute(new Position());
        return ((GetAllPositionsSO) so).getList();
    }

    public LinkedList<Player> getAllPlayers() throws Exception {
        AbstractGenericOperation so = new GetAllPlayersSO();
        so.templateExecute(new Player());
        return ((GetAllPlayersSO) so).getList();
    }

    public void updatePlayer(Player player) throws Exception {
        AbstractGenericOperation so = new UpdatePlayerSO();
        so.templateExecute(player);
    }

    public void deletePlayer(Player player) throws Exception {
        AbstractGenericOperation so = new DeletePlayerSO();
        so.templateExecute(player);
    }

    public void addTournament(Tournament tournament) throws Exception {
        AbstractGenericOperation so = new SaveTournamentSO();
        so.templateExecute(tournament);
    }

    public LinkedList<Tournament> getAllTournaments() throws Exception {
        AbstractGenericOperation so = new GetAllTournamentsSO();
        so.templateExecute(new Tournament());
        return ((GetAllTournamentsSO) so).getList();
    }

    
}
