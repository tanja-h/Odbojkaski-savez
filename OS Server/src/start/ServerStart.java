/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import domain.Employee;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import threads.HandleClientRequest;
import util.SettingsLoader;

/**
 *
 * @author TANJA-PC
 */
public class ServerStart extends Thread {

    private ServerSocket serverSocket;
    private boolean active;
    LinkedList<HandleClientRequest> clientsThreadsList;
    LinkedList<Employee> loggedEmployees;

    public ServerStart() {
        clientsThreadsList = new LinkedList<>();
        loggedEmployees = new LinkedList<>();
        //active = true;
    }

    public LinkedList<HandleClientRequest> getClientsThreadsList() {
        return clientsThreadsList;
    }

    public LinkedList<Employee> getLoggedEmployees() {
        return loggedEmployees;
    }

    public boolean isActive() {
        return active;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    

    @Override
    public void run() {
        try {
            int port = Integer.valueOf(SettingsLoader.getInstance().getProperty("port"));
            serverSocket = new ServerSocket(port);
            System.out.println("Server pokrenut!");
            active = true;
            try {
                while (active) {
                    Socket client = serverSocket.accept();
                    System.out.println("Klijent povezan.");
                    HandleClientRequest okz = new HandleClientRequest(client);
                    clientsThreadsList.add(okz);
                    okz.start();
                }
            } catch (IOException ex) {
                System.out.println("Server iskljucen, nema povezivanja novih klijenata!");
            }
            
        } catch (IOException ex) {
            System.out.println("Server ne moze da se pokrene! ERROR");
        }

    }

    public void stopServer() {
        active = false;
        try {
            serverSocket.close();
            if(clientsThreadsList != null)  clientsThreadsList.clear();
        } catch (IOException ex) {
            System.out.println("Server ne moze da se zaustavi");
        }
    }
}
