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
public class Barang {
    
    private int IdBarang;
    private String Article;
    private String Deskripsi;
    private int Harga1;
    private int Harga2;
    private int Harga3;
    private int Stok;
    
    public int getIdBarang(){
        return IdBarang;
    }

    public String getArticle() {
        return Article;
    }

    public void setArticle(String Article) {
        this.Article = Article;
    }
    
    public void setIdBarang(int id){
        IdBarang = id;
    }
    
    public String getDeskripsi(){
        return Deskripsi;
    }
    
    public void setDeskripsi(String deskripsi){
        Deskripsi = deskripsi;
    }
    
    public int getHarga1(){
        return Harga1;
    }
    
    public void setHarga1(int harga){
        Harga1 = harga;
    }
    
    public int getHarga2(){
        return Harga2;
    }
    
    public void setHarga2(int harga){
        Harga2 = harga;
    }
    
    public int getHarga3(){
        return Harga3;
    }
    
    public void setHarga3(int harga){
        Harga3 = harga;
    }
    
    public int getStok(){
        return Stok;
    }
    
    public void setStok(int stok){
        Stok = stok;
    }
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into Barang (IdBarang,Article,Deskripsi,Harga1,Harga2,Harga3,Stok) values (?,?,?,?,?,?,?)");
            con.preparedstatement.setInt(1,this.IdBarang);
            con.preparedstatement.setString(2,this.Article);
            con.preparedstatement.setString(3,this.Deskripsi);
            con.preparedstatement.setInt(4,this.Harga1);
            con.preparedstatement.setInt(5,this.Harga2);
            con.preparedstatement.setInt(6,this.Harga3);
            con.preparedstatement.setInt(7,this.Stok);
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
            ResultSet rs = con.statement.executeQuery("Select IdBarang,Article,Deskripsi,Harga1,Harga2,Harga3,Stok from Barang");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getInt("IdBarang"));
                    row.add(rs.getString("Article"));
                    row.add(rs.getString("Deskripsi"));
                    row.add(rs.getInt("Harga1"));
                    row.add(rs.getInt("Harga2"));
                    row.add(rs.getInt("Harga3"));
                    row.add(rs.getInt("Stok"));
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from Barang where IdBarang = ? ");
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
            ResultSet rs = con.statement.executeQuery("select IdBarang,Article,Deskripsi,Harga1,Harga2,Harga3,Stok from Barang where IdBarang = '" + id +"'");
            while(rs.next()){
            this.IdBarang = rs.getInt("IdBarang");
            this.Deskripsi = rs.getString("Article");
            this.Deskripsi = rs.getString("Deskripsi");
            this.Harga1 = rs.getInt("Harga1");
            this.Harga2 = rs.getInt("Harga2");
            this.Harga3 = rs.getInt("Harga3");
            this.Stok = rs.getInt("Stok");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from Barang where IdBarang = '"+id+"'");
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Barang set Article = ? ,Deskripsi = ?, Harga1=? , Harga2=? , Harga3=? , Stok=? where IdBarang = ? ");
            con.preparedstatement.setString(1,Article);
            con.preparedstatement.setString(2,Deskripsi);
            con.preparedstatement.setInt(3,Harga1);
            con.preparedstatement.setInt(4,Harga2);
            con.preparedstatement.setInt(5,Harga3);
            con.preparedstatement.setInt(6,Stok);
            con.preparedstatement.setInt(7,id);
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
    
    public boolean tambahstok(int id,int jumlah){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Barang set Stok =(select stok from barang where idbarang = ?)+? where IdBarang = ? ");
            con.preparedstatement.setInt(1,id);
            con.preparedstatement.setInt(2,jumlah);
            con.preparedstatement.setInt(3,id);
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
    
    public boolean kurangstok(int id,int jumlah){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Barang set Stok =(select stok from barang where idbarang = ?)-? where IdBarang = ? ");
            con.preparedstatement.setInt(1,id);
            con.preparedstatement.setInt(2,jumlah);
            con.preparedstatement.setInt(3,id);
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
            ResultSet rs = con.statement.executeQuery("select max(idbarang) as jml from barang");
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
            ResultSet rs = con.statement.executeQuery("SELECT IdBarang,Article,Deskripsi,Harga1,Harga2,Harga3,Stok FROM Barang WHERE "+fld+" LIKE '%"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                row.add(rs.getInt("IdBarang"));
                    row.add(rs.getString("Article"));
                    row.add(rs.getString("Deskripsi"));
                    row.add(rs.getInt("Harga1"));
                    row.add(rs.getInt("Harga2"));
                    row.add(rs.getInt("Harga3"));
                    row.add(rs.getInt("Stok"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex){ex.printStackTrace(); return null;}
    }
}
