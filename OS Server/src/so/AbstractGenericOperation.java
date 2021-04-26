/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import db.IDBBroker;
import db.connection.DatabaseConnection;
import domain.GeneralDomainObject;

/**
 *
 * @author TANJA-PC
 */
public abstract class AbstractGenericOperation {
    protected IDBBroker dbbroker;
    
    public AbstractGenericOperation() {
        this.dbbroker = new DBBroker();
    }
    
    public final void templateExecute(GeneralDomainObject gdo) throws Exception{
        try {
            validate(gdo);
            startTransaction();
            execute(gdo);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }
    
    private void startTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }
    private void commitTransaction() throws Exception {
        DatabaseConnection.getInstance().commit();
    }
    private void rollbackTransaction() throws Exception {
        DatabaseConnection.getInstance().rollback();
    }
    
    protected abstract void validate(GeneralDomainObject gdo) throws Exception;
    protected abstract void execute(GeneralDomainObject gdo) throws Exception;
}


