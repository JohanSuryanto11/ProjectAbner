package models;

import java.sql.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Koneksi {
    public Connection dbKoneksi;
    public Statement statement;
    public PreparedStatement preparedstatement;
    
    public Koneksi(){this.dbKoneksi = null;}
    public void bukaKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            dbKoneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void tutupKoneksi(){
        try{
            if(statement != null) statement.close();
            if(preparedstatement != null) preparedstatement.close();
            if(dbKoneksi != null) dbKoneksi.close();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
