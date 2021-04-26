/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author TANJA-PC
 */
public class CommunicationC {

    private static CommunicationC instance;
    Socket socket;
    HandleServerResponse hsr;

    public static CommunicationC getInstance() {
        if (instance == null) {
            instance = new CommunicationC();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    private CommunicationC() {
    }

    public void connect() throws IOException {
        socket = new Socket("localhost", 9000);
        System.out.println("[K] povezan na server");
        hsr = new HandleServerResponse();
        hsr.start();

    }

    public void sendRequest(ClientRequest cr) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(cr);
        } catch (IOException ex) {
            System.out.println("puko IO [K:posaljiZahtev]");
            hsr.stopHandler();
        }
    }

    public ServerResponse readResponse() {           //prijem odgovora
        ServerResponse sr = new ServerResponse();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            sr = (ServerResponse) ois.readObject();
        } catch (IOException ex) {
            System.out.println("puko IO [K:primiOdgovor]");
            hsr.stopHandler();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommunicationC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sr;
    }

}
