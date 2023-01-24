/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DomainObject;

/**
 *
 * @author HP
 */
public class PlatniPromet extends DomainObject{
    
    private int IdPlatniPromet;
    private String banka;
    private long racun;

    public PlatniPromet() {
    }

    public PlatniPromet(int IdPlatniPromet, String banka, long racun) {
        this.IdPlatniPromet = IdPlatniPromet;
        this.banka = banka;
        this.racun = racun;
    }
    
    public int getIdPlatniPromet() {
        return IdPlatniPromet;
    }

    public void setIdPlatniPromet(int IdPlatniPromet) {
        this.IdPlatniPromet = IdPlatniPromet;
    }

    public String getBanka() {
        return banka;
    }

    public void setBanka(String banka) {
        this.banka = banka;
    }

    public long getRacun() {
        return racun;
    }

    public void setRacun(long racun) {
        this.racun = racun;
    }

    @Override
    public String getTableName() {
        return "platniPromet pp";
    }

    @Override
    public String getAllColumnNames() {
        return "pp.IdPlatniPromet AS Id, pp.Banka AS Banka, pp.Racun.get_brojRacuna() AS Racun";
    }
    
    @Override
    public String getOrderByColumn() {
        return "pp.IdPlatniPromet";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("Id");
            String banka = rs.getString("Banka");
            long racun = rs.getLong("Racun");
            
            PlatniPromet pp = new PlatniPromet(id, banka, racun);
            list.add(pp);
        }
        
        return list;
    }
    
    @Override
    public String getIdWhereClause() {
        return "pp.Banka = '" + banka + "' AND pp.Racun.get_brojRacuna() = " + racun;
    }
    
     @Override
    public int getCountFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;
        
        while(rs.next()){
            id = rs.getInt("COUNT(1)");
        }
        
        return id;
    }

    @Override
    public String getIdColumnName() {
        return "pp.IdPlatniPromet";
    }

    @Override
    public String getMaxIdWhere() {
        return "";
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
        
        int id = 0;
        
        while(rs.next()){
            id = rs.getInt("Max");
        }
        
        return id;
    }

    @Override
    public String getInsertColumnNames() {
        return "pp.IdPlatniPromet, pp.Banka, pp.Racun";
    }

    @Override
    public String getColumnValues() {
        return "'" + banka + "', Racun(" + racun + ")"; 
    }

    @Override
    public String getUpdateClause() {
        return "pp.Banka = '" + banka + "', pp.Racun.brojRacuna = " + racun;
    }

    @Override
    public String getUpdateWhereClause() {
        return "pp.IdPlatniPromet = " + IdPlatniPromet;
    }
    
    @Override
    public String getDeleteWhereClause() {
        return "pp.Racun.brojRacuna = " + racun;
    }
    
    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    
    
}
