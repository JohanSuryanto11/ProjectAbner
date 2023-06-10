/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class Faktur {
    
    private int noFaktur;
    private String namaToko;
    private int idSales;
    private int idCustomer;
    private Date tanggal;
    private int diskon;
    private int totalBayar;
    private String status;

    public int getDiskon() {
        return diskon;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    
    public int getIdCustomer() {
        return idCustomer;
    }

    public int getIdSales() {
        return idSales;
    }

    public int getNoFaktur() {
        return noFaktur;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdSales(int idSales) {
        this.idSales = idSales;
    }

    public void setNoFaktur(int noFaktur) {
        this.noFaktur = noFaktur;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }
    
    
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into Faktur (noFaktur,namaToko,idSales,idCustomer,tanggal,diskon,totalBayar,status) values (?,?,?,?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.noFaktur);
            con.preparedstatement.setString(2,this.namaToko);
            con.preparedstatement.setInt(3,this.idSales);
            con.preparedstatement.setInt(4,this.idCustomer);
            con.preparedstatement.setDate(5,this.tanggal);
            con.preparedstatement.setInt(6,this.diskon);
            con.preparedstatement.setInt(7,this.totalBayar);
            con.preparedstatement.setString(8,this.status);
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

    public boolean Selesai(int id,String selesai){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Faktur set Status = ? where noFaktur = ? ");
            con.preparedstatement.setString(1, selesai);
            con.preparedstatement.setInt(2,id);
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
    
    public Vector Load(){
        try{
            Vector tableData=new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();;
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select f.noFaktur,f.namaToko,s.nama as namasales,c.nama as namacustomer,f.tanggal,f.diskon,f.totalBayar,f.status from Faktur f, sales s, customer c where f.idcustomer=c.idcustomer and f.idsales=s.idsales");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getInt("noFaktur"));
                    row.add(rs.getString("namaToko"));
                    row.add(rs.getString("namasales"));
                    row.add(rs.getString("namacustomer"));
                    row.add(rs.getDate("tanggal"));
                    row.add(rs.getInt("diskon"));
                    row.add(rs.getInt("totalBayar"));
                    row.add(rs.getString("status"));
                    tableData.add(row);
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from Faktur where noFaktur = ? ");
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
            ResultSet rs = con.statement.executeQuery("select noFaktur,idSales,idCustomer,tanggal,diskon,totalBayar,status from Faktur where noFaktur = '" + id +"'");
            while(rs.next()){
            this.noFaktur = rs.getInt("noFaktur");
            this.idSales = rs.getInt("idSales");
            this.idCustomer = rs.getInt("idCustomer");
            this.tanggal = rs.getDate("tanggal");
            this.diskon = rs.getInt("diskon");
            this.totalBayar = rs.getInt("totalBayar");
            this.status = rs.getString("status");
            }
            con.tutupKoneksi();
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int nofakturbaru(){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(nofaktur) as jml from Faktur");
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
            ResultSet rs = con.statement.executeQuery("Select f.noFaktur,s.nama as namasales,c.nama as namacustomer,f.tanggal,f.diskon,f.totalBayar,f.status from Faktur f, sales s, customer c where f.idcustomer=c.idcustomer and f.idsales=s.idsales and "+fld+" LIKE '"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                row.add(rs.getInt("noFaktur"));
                    row.add(rs.getString("namasales"));
                    row.add(rs.getString("namacustomer"));
                    row.add(rs.getDate("tanggal"));
                    row.add(rs.getInt("diskon"));
                    row.add(rs.getInt("totalBayar"));
                    row.add(rs.getString("status"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex){ex.printStackTrace(); return null;}
    }
}
