/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author johan
 */
public class Penjualan {
    private int idPenjualan;
    private int noFaktur;
    private int idBarang;
    private int jumlah;
    private int harga;

    public int getHarga() {
        return harga;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public int getIdPenjualan() {
        return idPenjualan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getNoFaktur() {
        return noFaktur;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setNoFaktur(int noFaktur) {
        this.noFaktur = noFaktur;
    }
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into penjualan (idPenjualan,noFaktur,idBarang,jumlah,harga) values (?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.idPenjualan);
            con.preparedstatement.setInt(2,this.noFaktur);
            con.preparedstatement.setInt(3,this.idBarang);
            con.preparedstatement.setInt(4,this.jumlah);
            con.preparedstatement.setInt(5,this.harga);
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
    
    public Vector Load(int nofaktur){
        try{
            Vector tableData=new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();;
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select b.article, b.deskripsi, p.jumlah, p.harga, p.jumlah*p.harga as jumlah_harga from faktur f, barang b, penjualan p where f.NoFaktur=p.NoFaktur and p.IdBarang=b.IdBarang and f.NoFaktur='"+nofaktur+"'");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getString("Article"));
                    row.add(rs.getString("Deskripsi"));
                    row.add(rs.getInt("jumlah"));
                    row.add(rs.getInt("harga"));
                    row.add(rs.getInt("jumlah_harga"));
                    tableData.add(row);
                    i++;}
                con.tutupKoneksi();
            return tableData;
        }catch (SQLException ex){
        ex.printStackTrace();
        return null;
        }
    }
    public int idpenjualanbaru(){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(idPenjualan) as jml from penjualan");
            while(rs.next()){
            val = rs.getInt("jml")+1;
            }
            con.tutupKoneksi();
        }catch (SQLException ex){
            ex.printStackTrace();
        }return val; 
    }
    public String[] Loaddata(int nofaktur){
        try{
            String[] data = new String[9];
            Koneksi con=new Koneksi();
            con.bukaKoneksi();;
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select f.nofaktur, f.namatoko, f.tanggal,c.nama as cnama,c.kota, c.provinsi, s.nama as snama, f.diskon, f.totalbayar from faktur f, customer c, penjualan p, sales s where f.NoFaktur=p.NoFaktur and f.IdCustomer=c.IdCustomer and s.IdSales=f.idSales  and f.NoFaktur='"+nofaktur+"'");
            int i=1;
            while (rs.next()){
                data[0]=rs.getString("nofaktur");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                data[1]=simpleDateFormat.format(rs.getDate("tanggal")).toString();
                data[2]=rs.getString("cnama");
                data[3]=rs.getString("snama");
                data[4]=rs.getString("kota");
                data[5]=rs.getString("provinsi");
                data[6]=Integer.toString(rs.getInt("diskon"));
                data[7]=Integer.toString(rs.getInt("totalbayar"));
                data[8]=rs.getString("namatoko");
                    i++;}
                con.tutupKoneksi();
            return data;
        }catch (SQLException ex){
        ex.printStackTrace();
        return null;
        }
    }
}
