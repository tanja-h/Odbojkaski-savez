/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TANJA-PC
 */
public class SettingsLoader {

    private static SettingsLoader instance;
    private Properties props;

    public static SettingsLoader getInstance() {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

    private SettingsLoader() {
        try {
            props = new Properties();
            props.load(new FileInputStream("settings.properties"));
        } catch (Exception ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key, "n/a");
    }

    public String getProperties() {
        String s = "";
        for (HashMap.Entry<Object, Object> entry : props.entrySet()) {
            s += entry.getKey().toString()+ "=" + entry.getValue().toString() + "\n";
        }
        return s;
    }
    
    public void updateProperties(String newProperties) throws Exception{
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("settings.properties");
            String[] npSplitted = newProperties.split("\n");
            for (int i = 0; i < npSplitted.length; i++) {
                //System.out.println("before splitting: " + npSplitted[i]);
                int index = npSplitted[i].indexOf("=");
                if (index == -1) {
                    throw new Exception("Neispravan format! Nedostaje znak \'=\' !");
                }
                String key = npSplitted[i].substring(0, index);
                String value = npSplitted[i].substring(index+1);
                System.out.println("[key: " + npSplitted[i].substring(0, index) + "]"
                        + " = [value: " + npSplitted[i].substring(index+1) + "]");
                props.setProperty(key, value);
            }
            System.out.println("");
            props.store(out, null);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("File not found!");
        } catch (IOException ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("IO greska!");
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception("IO greska kod zatvaranja izlaznog stream-a!");
            }
        }
    }
}
