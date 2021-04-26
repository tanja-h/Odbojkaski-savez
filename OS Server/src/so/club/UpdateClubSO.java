/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.club;

import domain.Club;
import domain.GeneralDomainObject;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class UpdateClubSO extends AbstractGenericOperation{

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Club)) {
            throw new Exception("Neispravan parametar za klub!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        dbbroker.update((Club) gdo);
    }
    
}
