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


public class FakturPembelian {
    
    private int noFaktur;
    private int idPabrik;
    private Date tanggal;
    private int diskon;
    private int totalBayar;
    private String status;

    public int getDiskon() {
        return diskon;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
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

    public void setIdPabrik(int idPabrik) {
        this.idPabrik = idPabrik;
    }

    public int getIdPabrik() {
        return idPabrik;
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into Fakturbeli (noFaktur,IdPabrik,tanggal,diskon,total,status) values (?,?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.noFaktur);
            con.preparedstatement.setInt(2,this.idPabrik);
            con.preparedstatement.setDate(3,this.tanggal);
            con.preparedstatement.setInt(4,this.diskon);
            con.preparedstatement.setInt(5,this.totalBayar);
            con.preparedstatement.setString(6,this.status);
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Fakturbeli set Status = ? where noFaktur = ? ");
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
            ResultSet rs = con.statement.executeQuery("Select f.noFaktur,c.namapabrik as namacustomer,f.tanggal,f.diskon,f.total,f.status from Fakturbeli f, Pabrik c where f.idpabrik=c.idpabrik");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getInt("noFaktur"));
                    row.add(rs.getString("namacustomer"));
                    row.add(rs.getDate("tanggal"));
                    row.add(rs.getInt("diskon"));
                    row.add(rs.getInt("total"));
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from Fakturbeli where noFaktur = ? ");
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
            ResultSet rs = con.statement.executeQuery("select noFaktur,idPabrik,tanggal,diskon,total,status from Fakturbeli where noFaktur = '" + id +"'");
            while(rs.next()){
            this.noFaktur = rs.getInt("noFaktur");
            this.idPabrik = rs.getInt("idPabrik");
            this.tanggal = rs.getDate("tanggal");
            this.diskon = rs.getInt("diskon");
            this.totalBayar = rs.getInt("total");
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
            ResultSet rs = con.statement.executeQuery("select max(nofaktur) as jml from Fakturbeli");
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
            ResultSet rs = con.statement.executeQuery("Select f.noFaktur,c.nama as namacustomer,f.tanggal,f.diskon,f.totalBayar,f.status from Faktur f, Pabrik c where f.idPabrik=c.idPabrik and "+fld+" LIKE '"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                row.add(rs.getInt("noFaktur"));
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
