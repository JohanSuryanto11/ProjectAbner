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
public class Customer {
    private int IdCustomer;
    private String Nama;
    private String Alamat;
    private String NoTelepon;
    private String Kota;
    private String Provinsi;

    public Customer(int idCustomer, String Nama, String Alamat, String NoTelepon, String Kota, String Provinsi) {
        this.IdCustomer = idCustomer;
        this.Nama = Nama;
        this.Alamat = Alamat;
        this.NoTelepon = NoTelepon;
        this.Kota = Kota;
        this.Provinsi = Provinsi;
    }

    public Customer() {
        
    }

    public int getIdCustomer() {
        return IdCustomer;
    }

    public void setIdCustomer(int IdCustomer) {
        this.IdCustomer = IdCustomer;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getNoTelepon() {
        return NoTelepon;
    }

    public void setNoTelepon(String NoTelepon) {
        this.NoTelepon = NoTelepon;
    }

    public String getKota() {
        return Kota;
    }

    public void setKota(String Kota) {
        this.Kota = Kota;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String Provinsi) {
        this.Provinsi = Provinsi;
    }

    
    
    
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into customer (IdCustomer, Nama, Alamat, NoTelepon, Kota, Provinsi) values (?,?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.IdCustomer);
            con.preparedstatement.setString(2,this.Nama);
            con.preparedstatement.setString(3,this.Alamat);
            con.preparedstatement.setString(5,this.NoTelepon);
            con.preparedstatement.setString(4,this.Kota);
            con.preparedstatement.setString(6,this.Provinsi);
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
            ResultSet rs = con.statement.executeQuery("Select IdCustomer, Nama, Alamat, NoTelepon, Kota, Provinsi from customer");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getInt("IdCustomer"));
                    row.add(rs.getString("Nama"));
                    row.add(rs.getString("Alamat"));
                    row.add(rs.getString("NoTelepon"));
                    row.add(rs.getString("Kota"));
                    row.add(rs.getString("Provinsi"));
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from customer where IdCustomer = ? ");
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
            ResultSet rs = con.statement.executeQuery("select Nama, Alamat, NoTelepon, Kota, Provinsi from customer where IdCustomer = '" + id +"'");
            while(rs.next()){
            this.Nama = rs.getString("Nama");
            this.Alamat = rs.getString("Alamat");
            this.NoTelepon = rs.getString("NoTelepon");
            this.Kota = rs.getString("Kota");
            this.Provinsi = rs.getString("Provinsi");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from customer where IdCustomer = '"+id+"'");
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("update customer set Nama = ? ,Alamat = ?, NoTelepon = ?, Kota=?, Provinsi = ? where IdCustomer = ? ");
            con.preparedstatement.setString(1,this.Nama);
            con.preparedstatement.setString(2,this.Alamat);
            con.preparedstatement.setString(4,this.NoTelepon);
            con.preparedstatement.setString(3,this.Kota);
            con.preparedstatement.setString(5,this.Provinsi);
            con.preparedstatement.setInt(6,id);
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
            ResultSet rs = con.statement.executeQuery("select max(idcustomer) as jml from customer");
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
            ResultSet rs = con.statement.executeQuery("SELECT IdCustomer, Nama, Alamat, NoTelepon, Kota, Provinsi FROM customer WHERE "+fld+" LIKE '%"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                    row.add(rs.getInt("IdCustomer"));
                    row.add(rs.getString("Nama"));
                    row.add(rs.getString("Alamat"));
                    row.add(rs.getString("NoTelepon"));
                    row.add(rs.getString("Kota"));
                    row.add(rs.getString("Provinsi"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex){ex.printStackTrace(); return null;}
    }
}
