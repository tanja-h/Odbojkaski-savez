/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.Employee;
import constants.Operation;
import domain.City;
import domain.Club;
import domain.Player;
import domain.Position;
import domain.Tournament;
import java.util.LinkedList;
import logic.ControlerC;
import session.Session;
import transfer.ServerResponse;



/**
 *
 * @author TANJA-PC
 */
public class HandleServerResponse extends Thread{

    boolean active = true;
    
    @Override
    public void run() {
        while (active) {
            ServerResponse sr = CommunicationC.getInstance().readResponse();
            
            switch(sr.getOperation()){
                case Operation.LOGIN:
                    if (sr.isSuccessful()) {
                        ControlerC.getInstance().loginEmployee((Employee)sr.getData());
                    }else{
                        ControlerC.getInstance().loginError(sr.getError());
                    }
                    break;
                    
                case Operation.LOGOUT:
                    ControlerC.getInstance().logoutEmployee(sr.isSuccessful());
                    active = false;
                    break;
                    
                case Operation.GET_ALL_CITIES:
                    if (sr.isSuccessful()) {
                        LinkedList<City> cities = (LinkedList<City>) sr.getData();
                        ControlerC.getInstance().getAllCities(cities);
                    }else{
                        ControlerC.getInstance().getAllCitiesError(sr.getError());
                    }
                    break;
                  
                case Operation.ADD_CLUB:
                    if (sr.isSuccessful()) {
                        String status = "Klub je uspesno registrovan!";
                        ControlerC.getInstance().clubAdded(status);
                    }else{
                        String status = sr.getError().getMessage();
                        ControlerC.getInstance().clubAddedError(status);
                    }
                    break;
                    
                case Operation.GET_ALL_CLUBS:
                    if (sr.isSuccessful()) {
                        LinkedList<Club> clubs = (LinkedList<Club>) sr.getData();
                        ControlerC.getInstance().getAllClubs(clubs);
                    }else{
                        ControlerC.getInstance().setTblClubsError(sr.getError().getMessage());
                    }
                    break;
                
                case Operation.SEARCH_CLUBS:
                    if (sr.isSuccessful()) {
                        LinkedList<Club> clubs = (LinkedList<Club>) sr.getData();
                        ControlerC.getInstance().refreshTblSearchClubs(clubs);
                        System.out.println(clubs);
                    }else{
                        ControlerC.getInstance().setTblClubsError(sr.getError().getMessage());
                    }
                    break;     
                    
                case Operation.UPDATE_CLUB:
                    String status1;
                    if (sr.isSuccessful()) {
                        status1 = "Podaci o klubu su izmenjeni.";
                    }else{
                        status1 = sr.getError().getMessage();
                    }
                    ControlerC.getInstance().clubUpdated(status1);
                    
                    break;
                    
                case Operation.ADD_CITY:
                    if (sr.isSuccessful()) {
                        LinkedList<City> cities = (LinkedList<City>) sr.getData();
                        Session.getInstance().getUseCaseParams().put("cities", cities);
                        ControlerC.getInstance().cityAdded(cities, "Grad je dodat!");
                    }else{
                        ControlerC.getInstance().cityAddedError(sr.getError());
                    }
                    break;
                
                case Operation.ADD_PLAYER:
                    if (sr.isSuccessful()) {
                        ControlerC.getInstance().playerAdded("Igrač je uspešno registrovan!");
                    }else{
                        ControlerC.getInstance().playerAddedError(sr.getError());
                    }
                    break;    
                    
                case Operation.GET_ALL_POSITIONS:
                    if (sr.isSuccessful()) {
                        LinkedList<Position> positions = (LinkedList<Position>) sr.getData();
                        ControlerC.getInstance().getAllPositions(positions);
                    }else{
                        ControlerC.getInstance().getAllPositionsError(sr.getError());
                    }
                    break;  
                
                /*   
                case Operation.GET_ALL_PLAYERS:
                    if (sr.isSuccessful()) {
                        LinkedList<Player> players = (LinkedList<Player>) sr.getData();
                        ControlerC.getInstance().setTblSearchPlayers(players);
                    }else{
                        ControlerC.getInstance().setTblPlayersError(sr.getError().getMessage());                        
                    }
                    break;  */
                    
                case Operation.GET_ALL_PLAYERS:    
                case Operation.SEARCH_PLAYERS:
                    if (sr.isSuccessful()) {
                        LinkedList<Player> players = (LinkedList<Player>) sr.getData();
                        ControlerC.getInstance().setTblSearchPlayers(players);
                        System.out.println(players);
                    }else{
                        ControlerC.getInstance().setTblPlayersError(sr.getError().getMessage());
                    }
                    break;     
                
                case Operation.UPDATE_PLAYER:
                    String status2;
                    if (sr.isSuccessful()) {
                        status2 = "Podaci o igraču su izmenjeni.";
                        LinkedList<Player> players = (LinkedList<Player>) sr.getData();
                        ControlerC.getInstance().playerUpdated(status2, players);
                    }else{
                        String status = sr.getError().getMessage();
                        ControlerC.getInstance().playerUpdatedError(status);
                    }
                    break; 
                    
                case Operation.DELETE_PLAYER:
                    String status;
                    if (sr.isSuccessful()) {
                        status = "Igrač je uspešno obrisan!";
                    }else{
                        status = sr.getError().getMessage();
                    }
                    ControlerC.getInstance().playerDeleted(status);
                    break; 
                    
                case Operation.ADD_TOURNAMENT:
                    if (sr.isSuccessful()) {
                        ControlerC.getInstance().tournamentAdded("Turnir je uspešno sačuvan!");
                    }else{
                        ControlerC.getInstance().tournamentAddedError(sr.getError().getMessage());
                    }
                    break;  
                    
                case Operation.GET_ALL_TOURNAMENTS:
                    if (sr.isSuccessful()) {
                        LinkedList<Tournament> tournaments = (LinkedList<Tournament>) sr.getData();
                        ControlerC.getInstance().getAllTournaments(tournaments);
                    }else{
                        ControlerC.getInstance().getAllTournamentsError(sr.getError().getMessage());
                    }
                    break;      
            }
            
        }
    }

    public void stopHandler() {
        active = false;
    }
    
    
    
}
