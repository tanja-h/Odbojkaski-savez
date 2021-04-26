/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author TANJA-PC
 */
public class ClockThread extends Thread{
    JLabel label;

    public ClockThread(JLabel label) {
        this.label = label;
    }
    
    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        while(true){
            label.setText(sdf.format(new Date()));
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
