/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.player;

import domain.GeneralDomainObject;
import domain.Player;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class DeletePlayerSO extends AbstractGenericOperation{
    
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Player)) {
            throw new Exception("Neispravan parametar za igraca!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> databasePlayers = dbbroker.getAll(gdo);
        Player p = (Player) gdo;
        if(!databasePlayers.contains(p)){
            throw new Exception("Igrač ne postoji u bazi ili je u međuvremenu obrisan!");
        }
        
        dbbroker.delete((Player) gdo);
    }
    
    
}
