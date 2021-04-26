/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.city;

import domain.City;
import domain.GeneralDomainObject;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class GetAllCitiesSO extends AbstractGenericOperation {

    private LinkedList<City> list;

    public GetAllCitiesSO() {
        list = new LinkedList<>();
    }

    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof City)) {
            throw new Exception("Neispravan parametar za grad!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.getAll((City) gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            City c = (City) generalDomainObject;
            list.add(c);
        }
    }

    public LinkedList<City> getList() {
        System.out.println("Vracena lista gradova!");
        return list;
    }

}
