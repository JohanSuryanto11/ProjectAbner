package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

    

public class Pekerja {
    private int IdPekerja;
    private String Nama;
    private String Alamat;
    private String NoTelepon;
    private Date TglLahir;
    private String Status;

    public Pekerja(int IdPekerja, String Nama, String Alamat, String NoTelepon, Date TglLahir, String Status) {
        this.IdPekerja = IdPekerja;
        this.Nama = Nama;
        this.Alamat = Alamat;
        this.NoTelepon = NoTelepon;
        this.TglLahir = TglLahir;
        this.Status = Status;
    }

    public Pekerja() {
        
    }

    public int getIdPekerja() {
        return IdPekerja;
    }

    public void setIdPekerja(int IdPekerja) {
        this.IdPekerja = IdPekerja;
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

    public Date getTglLahir() {
        return TglLahir;
    }

    public void setTglLahir(Date TglLahir) {
        this.TglLahir = TglLahir;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    public Vector loadData(){
        try {
            Vector tableData = new Vector();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            
            ResultSet rs = con.statement.executeQuery("select * from pekerja");
            int i=1;
            while (rs.next()){

                Vector<Object> row = new Vector<Object>();
                row.add(rs.getString("IdPekerja"));
                row.add(rs.getString("Nama"));
                row.add(rs.getString("Alamat"));
                row.add(rs.getString("NoTelepon"));
                row.add(rs.getDate("TglLahir"));
                row.add(rs.getString("Status"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            
        }
    }

    public int idbaru(){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(idpekerja) as jml from pekerja");
            while(rs.next()){
            val = rs.getInt("jml")+1;
            }
            con.tutupKoneksi();
        }catch (SQLException ex){
            ex.printStackTrace();
        }return val; 
    }
    
    public boolean insertData(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            
            con.preparedstatement = con.dbKoneksi.prepareStatement("insert into pekerja(IdPekerja,Nama,Alamat,NoTelepon,TglLahir,Status) values (?,?,?,?,?,?)");
            con.preparedstatement.setInt(1, this.IdPekerja);
            con.preparedstatement.setString(2, this.Nama);
            con.preparedstatement.setString(3, this.Alamat);            
            con.preparedstatement.setString(4, this.NoTelepon);            
            con.preparedstatement.setDate(5, this.TglLahir);            
            con.preparedstatement.setString(6, this.Status);            
            con.preparedstatement.executeUpdate();
            
            berhasil=true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil=false;
        }
        finally{
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean deleteData(int IdPekerja){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from pekerja where IdPekerja = ?");
            con.preparedstatement.setInt(1, IdPekerja);
            con.preparedstatement.executeUpdate();
            berhasil = true;
        }
        catch(Exception e){
            e.printStackTrace();
            berhasil = false;
        }
        finally{
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public int validasiIdPekerja(String IdPekerja){
        int val = 0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select count(*) as jml from pekerja where IdPekerja= '"+IdPekerja+"'");
            while(rs.next()){
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return val;
    }
    
    public boolean updateData(String IdPekerja){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update pekerja set Nama=?, Alamat=?, NoTelepon=?,TglLahir=?, Status=? where IdPekerja=?");
            con.preparedstatement.setString(1, this.Nama);
            con.preparedstatement.setString(2, this.Alamat);
            con.preparedstatement.setString(3, this.NoTelepon);
            con.preparedstatement.setDate(4, this.TglLahir);
            con.preparedstatement.setString(5, this.Status);
            con.preparedstatement.setInt(6, this.IdPekerja);
            con.preparedstatement.executeUpdate();
            berhasil=true;
        }
        catch (Exception e){
            e.printStackTrace();
            berhasil=false;
        }
        finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public boolean selectData(int IdPekerja){
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select IdPekerja,Nama,Alamat,NoTelepon,TglLahir,Status from pekerja where IdPekerja = '"+IdPekerja+"'");
            while(rs.next()){
                this.IdPekerja = rs.getInt("IdPekerja");
                this.Nama = rs.getString("Nama");
                this.Alamat = rs.getString("Alamat");
                this.NoTelepon = rs.getString("NoTelepon");
                this.TglLahir = rs.getDate("TglLahir");
                this.Status = rs.getString("Status");
            }
            con.tutupKoneksi();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public Vector LookUp(String fld, String dt){
        try{
            Vector tableData = new Vector();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from pekerja where " +fld+" like '%"+dt+"%'");
            int i = 1;
            while(rs.next()){ 
                Vector<Object> row = new Vector<Object>();
                row.add(rs.getInt("IdPekerja"));
                row.add(rs.getString("Nama"));
                row.add(rs.getString("Alamat"));
                row.add(rs.getString("NoTelepon"));
                row.add(rs.getDate("TglLahir"));
                row.add(rs.getString("Status"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
