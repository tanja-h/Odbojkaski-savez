/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.club;

import domain.Club;
import domain.GeneralDomainObject;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class GetAllClubsSO extends AbstractGenericOperation {

    private LinkedList<Club> list;

    public GetAllClubsSO() {
        list = new LinkedList<>();
    }

    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Club)) {
            throw new Exception("Neispravan parametar za klub!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.getAll((Club) gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Club c = (Club) generalDomainObject;
            list.add(c);
        }
    }

    public LinkedList<Club> getList() {
        System.out.println("Vracena lista klubova!");
        return list;
    }

}
