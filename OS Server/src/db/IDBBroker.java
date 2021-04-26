/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.LinkedList;
import domain.GeneralDomainObject;

/**
 *
 * @author TANJA-PC
 */
public interface IDBBroker {
    public LinkedList<GeneralDomainObject> getAll(GeneralDomainObject ge) throws Exception;
    public void save(GeneralDomainObject gdo)throws Exception;
    public void update(GeneralDomainObject gdo)throws Exception;
    public LinkedList<GeneralDomainObject> search(GeneralDomainObject gdo, String criterion)throws Exception;
    public void delete(GeneralDomainObject gdo)throws Exception;
    
}
