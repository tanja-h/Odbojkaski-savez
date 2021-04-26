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
public class SearchPlayersSO extends AbstractGenericOperation {

    private LinkedList<Player> list;
    private String criterion;

    public SearchPlayersSO(String criterion) {
        list = new LinkedList<>();
        this.criterion = criterion;
    }

    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Player)) {
            throw new Exception("Neispravan parametar za igraca!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.search(gdo, criterion);
        System.out.println("execute: " + gdoList);
        
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Player p = (Player) generalDomainObject;
            list.add(p);
        }
    }

    public LinkedList<Player> getList() {
        System.out.println("Vracena lista igraca!");
        return list;
    }

}
