package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Pabrik {
    private int IdPabrik;
    private String NamaPabrik;
    private String Alamat;
    private String NoTelepon;

    public Pabrik(int IdPabrik, String NamaPabrik, String Alamat, String NoTelepon) {
        this.IdPabrik = IdPabrik;
        this.NamaPabrik = NamaPabrik;
        this.Alamat = Alamat;
        this.NoTelepon = NoTelepon;
    }

    public Pabrik() {
        
    }

    public int getIdPabrik() {
        return IdPabrik;
    }

    public void setIdPabrik(int IdPabrik) {
        this.IdPabrik = IdPabrik;
    }

    public String getNamaPabrik() {
        return NamaPabrik;
    }

    public void setNamaPabrik(String NamaPabrik) {
        this.NamaPabrik = NamaPabrik;
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
    
    public boolean insert(){
        boolean berhasil=false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("Insert Into pabrik (IdPabrik, NamaPabrik, Alamat, NoTelepon) values (?,?,?,?)");
            con.preparedstatement.setInt(1,this.IdPabrik);
            con.preparedstatement.setString(2,this.NamaPabrik);
            con.preparedstatement.setString(3,this.Alamat);
            con.preparedstatement.setString(4,this.NoTelepon);
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
    
    public int idbaru(){
        int val =0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(idpabrik) as jml from pabrik");
            while(rs.next()){
            val = rs.getInt("jml")+1;
            }
            con.tutupKoneksi();
        }catch (SQLException ex){
            ex.printStackTrace();
        }return val; 
    }
    
    public Vector Load(){
        try{
            Vector tableData=new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();;
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select IdPabrik,NamaPabrik,Alamat,NoTelepon from pabrik");
            int i=1;
            while (rs.next()){
                Vector<Object> row= new Vector<Object>();
                    row.add(rs.getInt("IdPabrik"));
                    row.add(rs.getString("NamaPabrik"));
                    row.add(rs.getString("Alamat"));
                    row.add(rs.getString("NoTelepon"));
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("delete from pabrik where IdPabrik = ? ");
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
            ResultSet rs = con.statement.executeQuery("select IdPabrik,NamaPabrik,Alamat,NoTelepon from pabrik where IdPabrik = '" + id +"'");
            while(rs.next()){
            this.IdPabrik = rs.getInt("IdPabrik");
            this.NamaPabrik = rs.getString("NamaPabrik");
            this.Alamat = rs.getString("Alamat");
            this.NoTelepon = rs.getString("NoTelepon");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from Pabrik where IdPabrik = '"+id+"'");
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
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Pabrik set NamaPabrik = ? ,Alamat = ?, NoTelepon=? where IdPabrik = ? ");
            con.preparedstatement.setString(1,NamaPabrik);
            con.preparedstatement.setString(2,Alamat);
            con.preparedstatement.setString(3,NoTelepon);
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
    
    public boolean tambahNoTelepon(int id,int jumlah){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update Pabrik set NoTelepon =(select NoTelepon from pabrik where IdPabrik = ?)+? where IdPabrik = ? ");
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
    
    public boolean kurangNoTelepon(int id,int jumlah){
        boolean berhasil = false;
        Koneksi con = new Koneksi ();
        try{
            con.bukaKoneksi();
            con.preparedstatement = con.dbKoneksi.prepareStatement("update pabrik set NoTelepon =(select NoTelepon from pabrik where IdPabrik = ?)-? where IdPabrik = ? ");
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
    
    public Vector LookUp(String fld,String dt){
        try{
            Vector tableData = new Vector();
            Koneksi con=new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT IdPabrik,NamaPabrik,Alamat,NoTelepon FROM pabrik WHERE "+fld+" LIKE '%"+dt+"%'");
            int i=1;
            while(rs.next()){
                Vector<Object> row = new Vector <Object>();
                row.add(rs.getInt("IdPabrik"));
                    row.add(rs.getString("NamaPabrik"));
                    row.add(rs.getString("Alaamt"));
                    row.add(rs.getInt("NoTelepon"));
                tableData.add(row);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex){ex.printStackTrace(); return null;}
    }
}
