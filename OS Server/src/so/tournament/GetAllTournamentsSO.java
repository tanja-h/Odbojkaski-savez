/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tournament;

import domain.GeneralDomainObject;
import domain.Match;
import domain.Tournament;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class GetAllTournamentsSO extends AbstractGenericOperation {
    
    private LinkedList<Tournament> list;

    public GetAllTournamentsSO() {
        list = new LinkedList<>();
    }

    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Tournament)) {
            throw new Exception("Neispravan parametar za turnir!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.getAll((Tournament) gdo);
        
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Tournament t = (Tournament) generalDomainObject;
            
            String criterion = "m.tournamentID = " + t.getTournamentID();
            LinkedList<GeneralDomainObject> gdoMatchList = dbbroker.search(new Match(),criterion);
            
            for (GeneralDomainObject gdoMatch : gdoMatchList) {
                Match m = (Match) gdoMatch;
                t.getMatches().add(m);
            }
            
            list.add(t);
        }
    }

    public LinkedList<Tournament> getList() {
        System.out.println("Vracena lista turnira!");
        return list;
    }

}
