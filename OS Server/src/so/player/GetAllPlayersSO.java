/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.player;

import so.position.*;
import domain.GeneralDomainObject;
import domain.Player;
import domain.Position;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class GetAllPlayersSO extends AbstractGenericOperation {

    private LinkedList<Player> list;

    public GetAllPlayersSO() {
        list = new LinkedList<>();
    }

    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Player)) {
            throw new Exception("Neispravan parametar za igrača!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.getAll((Player) gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Player p = (Player) generalDomainObject;
            list.add(p);
        }
    }

    public LinkedList<Player> getList() {
        System.out.println("Vracena lista igrača!");
        return list;
    }

}
