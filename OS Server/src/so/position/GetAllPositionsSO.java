/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.position;

import domain.GeneralDomainObject;
import domain.Position;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class GetAllPositionsSO extends AbstractGenericOperation {

    private LinkedList<Position> list;

    public GetAllPositionsSO() {
        list = new LinkedList<>();
    }

    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Position)) {
            throw new Exception("Neispravan parametar za poziciju!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.getAll((Position) gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Position p = (Position) generalDomainObject;
            list.add(p);
        }
    }

    public LinkedList<Position> getList() {
        System.out.println("Vracena lista pozicija!");
        return list;
    }

}
