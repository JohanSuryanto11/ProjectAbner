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
public class DetailReturPembelian {
    private int idDetail;
    private int noRetur;
    private int idBarang;
    private int jumlah;
    private int harga;

    public int getHarga() {
        return harga;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public int getIdRetur() {
        return noRetur;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public void setIdRetur(int idRetur) {
        this.noRetur = idRetur;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into DetailRetur (idDetail,noRetur,idBarang,jumlah,harga) values (?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.idDetail);
            con.preparedstatement.setInt(2,this.noRetur);
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
            ResultSet rs = con.statement.executeQuery("select b.article, b.deskripsi, p.jumlah, p.harga, p.jumlah*p.harga as jumlah_harga from Retur f, barang b, detailRetur p where f.noRetur=p.noRetur and p.IdBarang=b.IdBarang and f.noRetur='"+nofaktur+"'");
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
            ResultSet rs = con.statement.executeQuery("select max(idDetail) as jml from detailRetur");
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
            ResultSet rs = con.statement.executeQuery("select f.noRetur, f.namatoko, f.tanggal,c.nama as cnama,c.kota, c.provinsi, f.totalbayar from Retur f, customer c, detailRetur p where f.noRetur=p.noRetur and f.IdCustomer=c.IdCustomer and f.noRetur='"+nofaktur+"'");
            int i=1;
            while (rs.next()){
                data[0]=rs.getString("noRetur");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                data[1]=simpleDateFormat.format(rs.getDate("tanggal")).toString();
                data[2]=rs.getString("cnama");
                data[3]=rs.getString("kota");
                data[4]=rs.getString("provinsi");
                data[5]=Integer.toString(rs.getInt("totalbayar"));
                data[6]=rs.getString("namatoko");
                    i++;}
                con.tutupKoneksi();
            return data;
        }catch (SQLException ex){
        ex.printStackTrace();
        return null;
        }
    }
}
