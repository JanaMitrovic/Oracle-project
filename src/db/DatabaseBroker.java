/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DomainObject;
import model.entity.Filijala;
import model.entity.PlatniPromet;
import model.entity.PoreskiObveznik;

/**
 *
 * @author HP
 */
public class DatabaseBroker {

    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DatabaseBroker() {
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("properties.config");

            properties.load(fileInputStream);

            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");

            fileInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws Exception {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom uspostavljanja konekcije sa bazom!");
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom raskidanja konekcije sa bazom!");
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska - commit!");
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska - rollback!");
            }
        }
    }

    public List<DomainObject> getAll(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() + " ORDER BY " + object.getOrderByColumn();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined1(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() 
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1() + " ORDER BY " + object.getOrderByColumn();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public List<DomainObject> getJoined2(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() 
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1() 
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2() 
                    + " WHERE " + object.getWhereIdClause() + " ORDER BY " + object.getOrderByColumn();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined3(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() 
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2()
                    + " JOIN " + object.joinTable3() + " ON " + object.joinTableClause3();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public List<DomainObject> getJoined4(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() 
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2()
                    + " JOIN " + object.joinTable3() + " ON " + object.joinTableClause3()
                    + " JOIN " + object.joinTable4() + " ON " + object.joinTableClause4() + " ORDER BY " + object.getOrderByColumn();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int maxId(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(" + object.getIdColumnName() + ") AS Max FROM " + object.getTableName() + object.getMaxIdWhere();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getMaxIdFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int exists(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT COUNT(1) FROM " + object.getTableName() + " WHERE " + object.getIdWhereClause();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getCountFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int insert(DomainObject object) throws SQLException {
        try {
            int id = maxId(object);
            id++;
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.getTableName() + " (" + object.getInsertColumnNames() + ") VALUES (" + id + ", " + object.getColumnValues() + ")";
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public int insert2(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.getTableName() + " (" + object.getInsertColumnNames() + ") VALUES (" + object.getColumnValues() + ")";
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int getId(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getIdColumnName() + " FROM " + object.getTableName() + " WHERE " + object.getIdWhereClause();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getIdFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public int update(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + object.getTableName() + " SET " + object.getUpdateClause() + " WHERE " + object.getUpdateWhereClause();
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int delete(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + object.getTableName() + " WHERE " + object.getDeleteWhereClause();
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    

}
