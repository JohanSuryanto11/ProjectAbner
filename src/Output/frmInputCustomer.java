package Output;


import javax.swing.JOptionPane;
import models.Customer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johan
 */
public class frmInputCustomer extends javax.swing.JFrame {

    static frmInputCustomer getInstance() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    Customer customer = new Customer();
    
    public frmInputCustomer() {
        initComponents();
    }

    public void tipe(String tipe){
        if(tipe=="Input"){
            simpan.setText("Simpan");
            idcustomer.setText("");
            idcustomer.setEditable(true);
            nama.setText("");
            alamat.setText("");
            telp.setText("");
            kota.setText("");
        }else{
            simpan.setText("Update");
            customer.select(Integer.parseInt(tipe));
            idcustomer.setText(tipe);
            idcustomer.setEditable(false);
            nama.setText(customer.getNama());
            alamat.setText(customer.getAlamat());
            telp.setText(customer.getNoTelepon());
            kota.setText(customer.getKota());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idcustomer = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        kota = new javax.swing.JTextField();
        telp = new javax.swing.JTextField();
        simpan = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        provinsi = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Input Customer");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("Id Customer");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setText("Nama");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel4.setText("Alamat");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel5.setText("Kota");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel6.setText("Telp");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel7.setText("Provinsi");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));
        getContentPane().add(idcustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 141, -1));
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 141, -1));

        alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatActionPerformed(evt);
            }
        });
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 141, -1));
        getContentPane().add(kota, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 141, -1));
        getContentPane().add(telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 141, -1));

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        batal.setText("Batal");
        getContentPane().add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        provinsi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jawa Barat", "Jawa Tengah", "Jawa Timur" }));
        getContentPane().add(provinsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 141, -1));

        jButton1.setText("Id Baru");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatActionPerformed
        
    }//GEN-LAST:event_alamatActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        try{
            Integer.parseInt(idcustomer.getText());
            if(simpan.getText().equalsIgnoreCase("Simpan")){
            if(customer.validasiId(Integer.parseInt(idcustomer.getText()))<=0){
           customer.setIdCustomer(Integer.parseInt(idcustomer.getText()));
            customer.setAlamat(alamat.getText());
            customer.setKota(kota.getText());
            customer.setNama(nama.getText());
            customer.setProvinsi(provinsi.getItemAt(provinsi.getSelectedIndex()));
            customer.setNoTelepon(telp.getText());
            if(JOptionPane.showConfirmDialog(this, "Simpan Data?")==0&&customer.insert()){
                JOptionPane.showMessageDialog(this, "Data Berhasil disimpan");
            }else JOptionPane.showMessageDialog(this, "Data Gagal disimpan");
        }else {
            JOptionPane.showMessageDialog(this, "Id Customer Sudah Terdaftar");
        }
        } else{
            customer.setAlamat(alamat.getText());
            customer.setKota(kota.getText());
            customer.setNama(nama.getText());
            customer.setProvinsi(provinsi.getItemAt(provinsi.getSelectedIndex()));
            customer.setNoTelepon(telp.getText());
            if(JOptionPane.showConfirmDialog(this, "Simpan Data?")==0&&customer.update(Integer.parseInt(idcustomer.getText()))){
                JOptionPane.showMessageDialog(this, "Data Berhasil diupdate");
                
            }else JOptionPane.showMessageDialog(this, "Data Gagal diupdate");
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Masukan Data dengan benar");
        } 
        
        
        
    }//GEN-LAST:event_simpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        idcustomer.setText(Integer.toString(customer.idbaru()));
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmInputCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInputCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInputCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInputCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInputCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JButton batal;
    private javax.swing.JTextField idcustomer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField kota;
    private javax.swing.JTextField nama;
    private javax.swing.JComboBox<String> provinsi;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField telp;
    // End of variables declaration//GEN-END:variables
}