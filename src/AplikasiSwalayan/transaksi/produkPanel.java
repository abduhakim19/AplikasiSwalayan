/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiSwalayan.transaksi;

import AplikasiSwalayan.transaksi.*;
import database.koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hakim
 */
public class produkPanel extends javax.swing.JFrame {
    private DefaultTableModel data;
    private Connection conn = new koneksi().connect();
    private pembelian f;

    /**
     * Creates new form wow
     * @param frame
     */
    public produkPanel(pembelian frame) {
        initComponents();
        tabelKategori();
        this.f = frame;

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
        tabelProduk = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabelProduk.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelProdukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelProduk);

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
        });

        jLabel5.setText("Kata Kunci Nama Produk");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(43, 43, 43)
                        .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelProdukMouseClicked
        // TODO add your handling code here:
        int baris = tabelProduk.getSelectedRow();
        
        this.f.tKodeProduk.setText(tabelProduk.getModel().getValueAt(baris, 0).toString());
        this.f.tNamaProduk.setText(tabelProduk.getModel().getValueAt(baris, 1).toString()); 
        this.f.tHarga.setText(tabelProduk.getModel().getValueAt(baris, 3).toString()); 
        this.setVisible(false);
        
        
    }//GEN-LAST:event_tabelProdukMouseClicked

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        Object header[]={"Kode","NAMA PRODUK","STOK","HARGA BELI","HARGA JUAL", "KATEGORI","ID"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabelProduk.setModel(data);

        String sql="select*from produk inner join kategori on produk.kd_kategori = kategori.kd_kategori "
                + "where nama_produk like '%"+ tcari.getText() +"%'";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString("kd_produk");
                String kolom2=rs.getString("nama_produk");
                String kolom3=rs.getString("stok");
                String kolom4=rs.getString("harga_beli");
                String kolom5=rs.getString("harga_jual");
                String kolom6=rs.getString("nama_kategori");
                String kolom7=rs.getString("kd_kategori");
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
            tabelProduk.removeColumn(tabelProduk.getColumnModel().getColumn(6));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tcariKeyPressed

    public void tabelKategori(){
        Object header[]={"Kode","NAMA PRODUK","STOK","HARGA BELI","HARGA JUAL", "KATEGORI","ID"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabelProduk.setModel(data);
        
        String sql="select*from produk inner join kategori "
                        + "on produk.kd_kategori = kategori.kd_kategori ";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString("kd_produk");
                String kolom2=rs.getString("nama_produk");
                String kolom3=rs.getString("stok");
                String kolom4=rs.getString("harga_beli");
                String kolom5=rs.getString("harga_jual");
                String kolom6=rs.getString("nama_kategori");
                String kolom7=rs.getString("kd_kategori");
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
            tabelProduk.removeColumn(tabelProduk.getColumnModel().getColumn(6));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
    }
    
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
            java.util.logging.Logger.getLogger(produkPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(produkPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(produkPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(produkPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private pembelian f;
            public void run() {
                new produkPanel(this.f).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelProduk;
    private javax.swing.JTextField tcari;
    // End of variables declaration//GEN-END:variables
}
