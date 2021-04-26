/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import domain.Employee;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import constants.Operation;
import domain.City;
import domain.Club;
import domain.Player;
import domain.Position;
import domain.Tournament;
import java.util.LinkedList;
import logic.ControlerS;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author TANJA-PC
 */
public class HandleClientRequest extends Thread {

    private Socket socket;
    private boolean active;

    public HandleClientRequest(Socket clientSoket) {
        socket = clientSoket;
        active = true;
    }

    @Override
    public void run() {
        while (active) {
            ClientRequest cr = readRequest();
            ServerResponse sr = new ServerResponse();
            sr.setOperation(cr.getOperation());

            switch (cr.getOperation()) {
                case Operation.LOGIN:
                    try {
                        Employee e = ControlerS.getInstance().loginEmployee((Employee) cr.getParameter());
                        sr.setData(e);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.LOGOUT:
                    Employee e = (Employee) cr.getParameter();
                    System.out.println("hcr: " + e + " je odjavljen!");
                    ControlerS.getInstance().logoutEmployee(e);
                    sr.setSuccessful(true); //na zahtev klijenta
                    //sr.setError(new Exception("Uspesno ste se odjavili!"));
                    break;

                case Operation.GET_ALL_CITIES:
                    try {
                        LinkedList<City> cities = ControlerS.getInstance().getAllCities();
                        sr.setData(cities);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.ADD_CLUB:
                    Club club = (Club) cr.getParameter();
                    try {
                        ControlerS.getInstance().addClub(club);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                
                
                case Operation.GET_ALL_CLUBS:    
                    try {
                        LinkedList<Club> clubs = ControlerS.getInstance().getAllClubs();
                        sr.setData(clubs);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                    
                case Operation.UPDATE_CLUB:
                    Club cl = (Club) cr.getParameter();
                    try {
                        ControlerS.getInstance().updateClub(cl);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;   
                 
                case Operation.SEARCH_CLUBS:
                    String criterion = (String) cr.getParameter();
                    try {
                        LinkedList<Club> clubs = ControlerS.getInstance().searchClubs(criterion);
                        sr.setData(clubs);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;   
                    
                case Operation.ADD_CITY:
                    City city = (City) cr.getParameter();
                    try {
                        ControlerS.getInstance().addCity(city);
                        LinkedList<City> cities = ControlerS.getInstance().getAllCities();
                        sr.setData(cities);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break; 
                    
                case Operation.ADD_PLAYER:
                    Player player = (Player) cr.getParameter();
                    try {
                        ControlerS.getInstance().addPlayer(player);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;  
                
                case Operation.GET_ALL_PLAYERS:
                    try {
                        LinkedList<Player> players = ControlerS.getInstance().getAllPlayers();
                        sr.setData(players);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;    
                    
                case Operation.SEARCH_PLAYERS:
                    String criterion1 = (String) cr.getParameter();
                    try {
                        LinkedList<Player> players = ControlerS.getInstance().searchPlayers(criterion1);
                        sr.setData(players);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                    
                case Operation.GET_ALL_POSITIONS:
                    try {
                        LinkedList<Position> positions = ControlerS.getInstance().getAllPositions();
                        sr.setData(positions);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;    
                    
                case Operation.UPDATE_PLAYER:
                    Player pl = (Player) cr.getParameter();
                    try {
                        ControlerS.getInstance().updatePlayer(pl);
                        LinkedList<Player> players = ControlerS.getInstance().getAllPlayers();
                        sr.setData(players);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break; 
                
                case Operation.DELETE_PLAYER:
                    Player p = (Player) cr.getParameter();
                    try {
                        ControlerS.getInstance().deletePlayer(p);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                
                case Operation.ADD_TOURNAMENT:
                    Tournament tournament = (Tournament) cr.getParameter();
                    try {
                        ControlerS.getInstance().addTournament(tournament);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;   
                    
                case Operation.GET_ALL_TOURNAMENTS:
                    try {
                        LinkedList<Tournament> tournaments = ControlerS.getInstance().getAllTournaments();
                        sr.setData(tournaments);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;    
            }
            sendResponse(sr);
        }
    }

    public void sendResponse(ServerResponse sr) {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            ous.writeObject(sr);
            if (sr.getOperation() == Operation.LOGOUT) {
                active = false;
                socket.close();
                System.out.println("S:Klijent se odjavio!");
            }
        } catch (IOException ex) {
            System.out.println("Prekinut IO! [S:slanje odgovora]");
            active = false;
        }
    }

    public ClientRequest readRequest() {    //primi zahtev
        ClientRequest cr = new ClientRequest();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            cr = (ClientRequest) ois.readObject();
        } catch (IOException ex) {
            System.out.println("Prekinut IO! [S:primanje zahteva]");
            active = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cr;
    }

    public Socket getSocket() {
        return socket;
    }

    public void close() {
        try {
            active = false;
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
