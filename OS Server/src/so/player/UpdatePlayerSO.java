/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.player;

import domain.GeneralDomainObject;
import domain.Player;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class UpdatePlayerSO extends AbstractGenericOperation{

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Player)) {
            throw new Exception("Neispravan parametar za irača!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        dbbroker.update((Player) gdo);
    }
    
}
