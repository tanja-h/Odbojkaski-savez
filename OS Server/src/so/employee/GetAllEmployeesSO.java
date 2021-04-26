/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.employee;

import domain.Employee;
import domain.GeneralDomainObject;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author TANJA-PC
 */
public class GetAllEmployeesSO extends AbstractGenericOperation {

    private LinkedList<Employee> list;

    public GetAllEmployeesSO() {
        list = new LinkedList<>();
    }
    
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Employee)) {
            throw new Exception("Neispravan parametar za službenika!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        LinkedList<GeneralDomainObject> gdoList = dbbroker.getAll((Employee) gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Employee e = (Employee) generalDomainObject;
            list.add(e);
            System.out.println(e);
        }
    }

    public LinkedList<Employee> getList() {
        return list;
    }

}
