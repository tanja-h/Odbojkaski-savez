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
public class SaveClubSO extends AbstractGenericOperation{
    
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Club)) {
            throw new Exception("Neispravan parametar za klub!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> databaseClubs = dbbroker.getAll(gdo);
        for (GeneralDomainObject generalDomainObject : databaseClubs) {
            Club c = (Club) generalDomainObject;
            if(((Club)gdo).equals(c)){
                throw new Exception("Klub već postoji u bazi!");
            }
        }
        
        dbbroker.save((Club) gdo);
    }
    
    
}
