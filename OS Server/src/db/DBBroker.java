/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import domain.GeneralDomainObject;
import java.sql.PreparedStatement;

/**
 *
 * @author TANJA-PC
 */
public class DBBroker implements IDBBroker {

    @Override
    public LinkedList<GeneralDomainObject> getAll(GeneralDomainObject gdo) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "SELECT * FROM " + gdo.getTableName()
                + gdo.getJoinClause()
                + " ORDER BY " + gdo.getOrderByClause();
        System.out.println(query);

        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        LinkedList<GeneralDomainObject> list = gdo.getObjectList(rs);

        rs.close();
        s.close();
        return list;
    }

    @Override
    public void save(GeneralDomainObject gdo) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "INSERT INTO " + gdo.getTableName()
                + "(" + gdo.getAttributesNames() + ")"
                + " VALUES (" + gdo.getAttributeValues() + ")";
        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        System.out.println(ps);

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(GeneralDomainObject gdo) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "UPDATE " + gdo.getTableName()
                + " SET " + gdo.getUpdateValues()
                + " WHERE " + gdo.getPrimaryKeyClause();
        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        System.out.println(ps);

        ps.executeUpdate();
        ps.close();
        System.out.println("Uspešno ste izmenili objekat u bazi!");

    }

    @Override
    public LinkedList<GeneralDomainObject> search(GeneralDomainObject gdo, String criterion) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "SELECT * FROM " + gdo.getTableName()
                + gdo.getJoinClause()
                + " WHERE " + criterion
                + " ORDER BY " + gdo.getOrderByClause();
        System.out.println(query);

        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        LinkedList<GeneralDomainObject> list = gdo.getObjectList(rs);

        rs.close();
        s.close();
        return list;
    }

    @Override
    public void delete(GeneralDomainObject gdo) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "DELETE FROM " + gdo.getTableName()
                + " WHERE " + gdo.getPrimaryKeyClause();
        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        System.out.println(ps);

        ps.executeUpdate();
        ps.close();
        System.out.println("Uspesno ste obrisali objekat iz baze!");
    }
    
}
