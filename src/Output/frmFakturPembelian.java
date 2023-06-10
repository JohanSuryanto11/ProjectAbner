package Output;


import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Faktur;
import models.FakturPembelian;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johan
 */
public class frmFakturPembelian extends javax.swing.JFrame {
    frmDetailFakturPembelian detail = new frmDetailFakturPembelian();
    FakturPembelian faktur = new FakturPembelian();
    public frmFakturPembelian() {
        initComponents();
        tampilkanDataAll();
    }
    
    public boolean tampilkanDataAll(){
     Vector<String> tableHeader = new Vector<String>();
        tableHeader.add("No Faktur");
        tableHeader.add("Nama Pabrik");
        tableHeader.add("Tanggal");
        tableHeader.add("Diskon");
        tableHeader.add("TotalBayar");
        tableHeader.add("Status");
        Vector tableData = faktur.Load();
        if(tableData!=null){
            tabelfaktur.setModel(new DefaultTableModel(tableData,tableHeader));
            DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.RIGHT);
            tabelfaktur.getColumnModel().getColumn(2).setMinWidth(200);
            return true;
        } else return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelfaktur = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        hapus = new javax.swing.JButton();
        detil = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmbcari = new javax.swing.JComboBox<>();
        dtcari = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelfaktur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelfaktur);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 131, 900, 456));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Faktur");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 11, -1, -1));

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 610, -1, -1));

        detil.setText("Detail");
        detil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detilActionPerformed(evt);
            }
        });
        getContentPane().add(detil, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 590, 140, 80));

        jLabel6.setText("Cari Berdasarkan :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 20));

        cmbcari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Faktur", "Nama Sales", "Nama Customer", "Tanggal", "Diskon", "Total Bayar", "Status" }));
        getContentPane().add(cmbcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 108, -1));
        getContentPane().add(dtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 120, -1));

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 97, -1, -1));

        jButton2.setText("test");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, -1));

        jButton3.setText("Selesai");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 590, 130, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Hapus Data Faktur"+tabelfaktur.getValueAt(tabelfaktur.getSelectedRow(), 3).toString())==0&&faktur.delete(Integer.parseInt(tabelfaktur.getValueAt(tabelfaktur.getSelectedRow(), 0).toString()))){
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            tampilkanDataAll();
        }else JOptionPane.showMessageDialog(this, "Data Gagal Dihapus");
    }//GEN-LAST:event_hapusActionPerformed

    private void detilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detilActionPerformed
        detail.setVisible(true);
        detail.tampilkanDataAll(Integer.parseInt(tabelfaktur.getValueAt(tabelfaktur.getSelectedRow(), 0).toString()));
        detail.tampilkandata(Integer.parseInt(tabelfaktur.getValueAt(tabelfaktur.getSelectedRow(), 0).toString()));
    }//GEN-LAST:event_detilActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        Vector<String> tableHeader = new Vector<String>();
        tableHeader.add("No Faktur");
        tableHeader.add("Nama Sales");
        tableHeader.add("Nama Customer");
        tableHeader.add("Tanggal");
        tableHeader.add("Diskon");
        tableHeader.add("TotalBayar");
        tableHeader.add("Status");
        String cari = "";
        switch(cmbcari.getSelectedIndex()){
            case 0: {
                cari = "f.nofaktur";
                break;
            }
            case 1: {
                cari = "s.nama";
                break;
            }
            case 2: {
                cari = "c.nama";
                break;
            }
            case 3: {
                cari = "f.tanggal";
                break;
            }
            case 4: {
                cari = "f.diskon";
                break;
            }
            case 5: {
                cari = "f.totalbayar";
                break;
            }
            case 6: {
                cari = "f.Status";
                break;
            }
        }
        Vector tableData = faktur.LookUp(cari, dtcari.getText());
        if(tableData!=null){
            tabelfaktur.setModel(new DefaultTableModel(tableData,tableHeader));
            DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.RIGHT);
            tabelfaktur.getColumnModel().getColumn(2).setMinWidth(200);
        } 

    }//GEN-LAST:event_cariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tampilkanDataAll();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frmFakturReturPenjualan retur = new frmFakturReturPenjualan();
        retur.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(tabelfaktur.getSelectedRow()>=0){
            int input = JOptionPane.showConfirmDialog(this, "Faktur ini Selesai?");
            if(input==0){
            faktur.Selesai(Integer.parseInt(tabelfaktur.getValueAt(tabelfaktur.getSelectedRow(), 0).toString()),"Selesai");
        tampilkanDataAll();
        }else if(input==1){
            faktur.Selesai(Integer.parseInt(tabelfaktur.getValueAt(tabelfaktur.getSelectedRow(), 0).toString()),"Belum Selesai");
            tampilkanDataAll();
        }
        }else JOptionPane.showMessageDialog(this, "Pilih faktur terlebih dahulu");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmFakturPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFakturPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFakturPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFakturPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmFakturPembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cari;
    private javax.swing.JComboBox<String> cmbcari;
    private javax.swing.JButton detil;
    private javax.swing.JTextField dtcari;
    private javax.swing.JButton hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelfaktur;
    // End of variables declaration//GEN-END:variables
}
