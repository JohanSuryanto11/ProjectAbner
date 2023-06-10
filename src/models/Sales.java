/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author johan
 */
public class Sales {
    private int IdSales;
    private String Nama;
    private String Telp;
    private String Alamat;
    
    public int getIdSales(){
        return IdSales;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getNama() {
        return Nama;
    }

    public String getTlp() {
        return Telp;
    }

    public void setIdSales(int IdSales) {
        this.IdSales = IdSales;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public void setTelp(String Telp) {
        this.Telp = Telp;
    }
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into Sales (IdSales,Nama,noTelepon,Alamat) values (?,?,?,?)");
            con.preparedstatement.setInt(1,this.IdSales);
            con.preparedstatement.setString(2,this.Nama);
            con.preparedstatement.setString(3,this.Telp);
            con.preparedstatement.setString(4,this.Alamat);
            con.preparedstatement.executeUpdate();
            berhasil = true;
        }catch(Exception e){
            e.printStackTrace();
            berhasil = false ;
        }
        finally {
            con.tutupKoneksi();
        return berhasil;
        }
    }

    public Vector Load(){
        try{
            Vector tableData=new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();;
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select IdSales,Nama,noTelepon,Alamat from Sales");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getString("IdSales"));
                    row.add(rs.getString("Nama"));
                    row.add(rs.getString("noTelepon"));
                    row.add(rs.getString("Alamat"));
                    tableData.add(row);
                    i++;}
                con.tutupKoneksi();
            return tableData;
        }catch (SQLException ex){
        ex.printStackTrace();
        return null;
        }
    }
    
    public Vector LoadNama(){
        try{
            Vector tableData=new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();;
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Nama from Sales");
            int i=1;
            while (rs.next()){
                    tableData.add(rs.getString("Nama"));
                    i++;}
                con.tutupKoneksi();
            return tableData;
        }catch (SQLException ex){
        ex.printStackTrace();
        return null;
        }
    }

    public boolean delete(int id){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from Sales where IdSales = ? ");
            con.preparedstatement.setInt(1,id);
            con.preparedstatement.executeUpdate();
            berhasil = true;
        } catch (Exception e){
            e.printStackTrace();
            berhasil = false;
        }finally{
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean select (int id){
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select IdSales,Nama,noTelepon,Alamat from Sales where IdSales = '" + id +"'");
            while(rs.next()){
            this.IdSales = rs.getInt("IdSales");
            this.Nama = rs.getString("Nama");
            this.Telp = rs.getString("noTelepon");
            this.Alamat = rs.getString("Alamat");
            }
            con.tutupKoneksi();
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int validasiId(int id){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from Sales where IdSales = '"+id+"'");
            while(rs.next()){
            val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        }catch (SQLException ex){
            ex.printStackTrace();
        }return val; 
    }

    public boolean update(int id){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Sales set Nama = ? , Alamat=? , noTelepon=? where IdSales = ? ");
            con.preparedstatement.setString(1,Nama);
            con.preparedstatement.setString(2,Alamat);
            con.preparedstatement.setString(3,Telp);
            con.preparedstatement.setInt(4,id);
            con.preparedstatement.executeUpdate();
            berhasil = true;
        } catch (Exception e){
            e.printStackTrace();
            berhasil = false;
        }finally{
            con.tutupKoneksi();
        return berhasil;
        }
    }

    public int idbaru(){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(idsales) as jml from sales");
            while(rs.next()){
            val = rs.getInt("jml")+1;
            }
            con.tutupKoneksi();
        }catch (SQLException ex){
            ex.printStackTrace();
        }return val; 
    }
    
    public Vector LookUp(String fld,String dt){
        try{
            Vector tableData = new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT IdSales,Nama,noTelepon,Alamat FROM Sales WHERE "+fld+" LIKE '%"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                row.add(rs.getInt("IdSales"));
                    row.add(rs.getString("Nama"));
                    row.add(rs.getString("noTelepon"));
                    row.add(rs.getString("Alamat"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex){ex.printStackTrace(); return null;}
    }
}
