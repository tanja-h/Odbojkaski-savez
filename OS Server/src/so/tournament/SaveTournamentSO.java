/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tournament;

import domain.Club;
import domain.GeneralDomainObject;
import domain.Match;
import domain.Participation;
import domain.Tournament;
import domain.domainEnum.Winner;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class SaveTournamentSO extends AbstractGenericOperation{
    
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Tournament)) {
            throw new Exception("Neispravan parametar za turnir!");
        }
        
        Tournament tournament = (Tournament) gdo;
        LinkedList<GeneralDomainObject> databaseTournaments = dbbroker.getAll(gdo);
        for (GeneralDomainObject generalDomainObject : databaseTournaments) {
            Tournament tDB = (Tournament) generalDomainObject;
            if(tournament.equals(tDB)){
                throw new Exception("ID turnira već postoji u bazi!");
            }
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        Tournament tournament = (Tournament) gdo;
        dbbroker.save(tournament);
        
        Club winnerT = tournament.getWinner();
        winnerT.setNumberOfWins_Tournament(winnerT.getNumberOfWins_Tournament()+1);
        dbbroker.update(winnerT);
        
        for (Match m : tournament.getMatches()) {
            dbbroker.save(m);
            
            Club winnerM;
            if (m.getWinner().equals(Winner.DOMAĆIN)) {
                winnerM = m.getHost();
            }else{
                winnerM = m.getGuest();
            }
            winnerM.setNumberOfWins_Match(winnerM.getNumberOfWins_Match()+1);
            dbbroker.update(winnerM);
        }
        
        LinkedList<Club> list = tournament.getParticipants();
        for (Club c : list) {
            Participation p = new Participation(c, tournament);
            dbbroker.save(p);
        }
        
    }
    
    
}
