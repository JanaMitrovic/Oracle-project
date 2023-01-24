/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public abstract class DomainObject implements Serializable {

    public abstract String getTableName();

    public abstract String getAllColumnNames();
    
    public abstract String getOrderByColumn();
    
    public abstract List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException;
    
    public abstract String joinTable1();
    
    public abstract String joinTableClause1();
    
    public abstract String joinTable2();
    
    public abstract String joinTableClause2();
    
    public abstract String joinTable3();
    
    public abstract String joinTableClause3();
    
    public abstract String joinTable4();
    
    public abstract String joinTableClause4();
    
    public abstract String getIdColumnName();
    
    public abstract String getMaxIdWhere();
    
    public abstract int getMaxIdFromResultSet(ResultSet rs) throws SQLException;
    
    public abstract int getIdFromResultSet(ResultSet rs) throws SQLException;
    
    public abstract int getCountFromResultSet(ResultSet rs) throws SQLException;
    
    public abstract String getIdWhereClause();
    
    public abstract String getInsertColumnNames();
    
    public abstract String getColumnValues();
    
    public abstract String getUpdateClause();
    
    public abstract String getWhereIdClause();
    
    public abstract String getUpdateWhereClause();
    
    public abstract String getDeleteWhereClause();


}
