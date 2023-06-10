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

/**
 *
 * @author johan
 */
public class ReturPembelian {
    private int noRetur;
    private int idCustomer;
    private Date tanggal;
    private int totalBayar;

    public int getIdCustomer() {
        return idCustomer;
    }


    public int getNoRetur() {
        return noRetur;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }


    public void setNoRetur(int noRetur) {
        this.noRetur = noRetur;
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into ReturPembelian (noRetur,idpabrik,tanggal,totalBayar) values (?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.noRetur);
            con.preparedstatement.setInt(2,this.idCustomer);
            con.preparedstatement.setDate(3,this.tanggal);
            con.preparedstatement.setInt(4,this.totalBayar);
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
            ResultSet rs = con.statement.executeQuery("Select f.noRetur,f.namaToko,f.tanggal,c.nama as namacustomer,f.totalBayar from Retur f, customer c where f.idcustomer=c.idcustomer");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getInt("noRetur"));
                    row.add(rs.getString("namaToko"));
                    row.add(rs.getInt("tanggal"));
                    row.add(rs.getString("namacustomer"));
                    row.add(rs.getInt("totalBayar"));
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from Returpembelian where noRetur = ? ");
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
            ResultSet rs = con.statement.executeQuery("select noRetur,idCustomer,tanggal,totalBayar from Returpembelian where noRetur = '" + id +"'");
            while(rs.next()){
            this.noRetur = rs.getInt("noRetur");
            this.idCustomer = rs.getInt("idpabrik");
            this.tanggal = rs.getDate("tanggal");
            this.totalBayar = rs.getInt("totalBayar");
            }
            con.tutupKoneksi();
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int noreturbaru(){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(noRetur) as jml from Returpembelian");
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
            ResultSet rs = con.statement.executeQuery("Select f.noRetur,f.namaToko,f.tanggal,c.nama as namacustomer,f.tanggal,f.totalBayar from Returpembelian f, pabrik c where f.idpabrik=c.idpabrik and "+fld+" LIKE '"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                row.add(rs.getInt("noRetur"));
                    row.add(rs.getInt("tanggal"));
                    row.add(rs.getString("namacustomer"));
                    row.add(rs.getDate("tanggal"));
                    row.add(rs.getInt("totalBayar"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex){ex.printStackTrace(); return null;}
    }
}
