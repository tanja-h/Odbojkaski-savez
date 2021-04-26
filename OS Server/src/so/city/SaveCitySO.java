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
public class SaveCitySO extends AbstractGenericOperation{
    
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof City)) {
            throw new Exception("Neispravan parametar za grad!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> databaseCities = dbbroker.getAll(gdo);
        for (GeneralDomainObject generalDomainObject : databaseCities) {
            City c = (City) generalDomainObject;
            if(((City)gdo).equals(c)){
                throw new Exception("Grad već postoji u bazi!");
            }
        }
        
        dbbroker.save((City) gdo);
    }
    
    
}
