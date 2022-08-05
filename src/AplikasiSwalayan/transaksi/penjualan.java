package AplikasiSwalayan.transaksi;

import AplikasiSwalayan.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import database.koneksi;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import session.UserSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hakim
 */
public class penjualan extends javax.swing.JInternalFrame {
    JasperReport JasperReport;
    JasperDesign JasperDesign;
    JasperPrint JasperPrint;
    Map<String, Object> param = new HashMap<>();
    private DefaultTableModel data;
    private Connection conn = new koneksi().connect();
    ResultSet rs;
    /**
     * Creates new form penjualan
     */
    public penjualan() {
        initComponents();
        
        
        this.setBorder(null);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        tabelPembelian();
        setKaryawan();
//        setTrans();
        
        utama();
        
    }
    
    protected void autonumber(){
             
            
            String sql="select*from transaksi_penjualan ORDER BY kd_transaksi_jual DESC";
            try {
                Statement stm = conn.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if (rs.next()) {
                String NoFaktur = rs.getString("kd_transaksi_jual").substring(3);
                String TR = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(TR.length()==1){
                    Nol = "000";
                }
                else if(TR.length()==2){
                    Nol = "00";
                }
                else if(TR.length()==3){
                    Nol = "0";
                }
                else if(TR.length()==4){
                    Nol = "";
                }
                tKodeTr.setText("TRJ" + Nol + TR);
            } else {
                tKodeTr.setText("TRJ0001");
            }
            rs.close();
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
           
    }
    
    protected void kosong(){
        tKodeProduk.setText("");
        tNamaProduk.setText("");
        tJumlah.setText("");
        tHarga.setText("");
        tTanggal.setDate(new Date());
    }
    
    
    protected void setKaryawan() {
        tKodeKaryawan.setText(String.valueOf(UserSession.getKd_karyawan()));
        tNamaKaryawan.setText(UserSession.getNama_karyawan());
    }
    
    protected void simpanTransaksi() {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");   
        String tgl = String.valueOf(fm.format(tTanggal.getDate()));
         
        String sql = "insert into transaksi_penjualan values (?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tKodeTr.getText());
            stat.setInt(2, Integer.valueOf(tKodeKaryawan.getText()));
            stat.setInt(3, Integer.valueOf(tKodeKonsumen.getText()));
            stat.setInt(4, Integer.valueOf(tBayar.getText()));
            stat.setString(5, tgl);

            stat.executeUpdate();
            
            String sql2 = "insert into transaksi_penjualan_detail values (?,?,?)";
            int baris = tabelPenjualan.getRowCount();
            
            for (int i = 0; i < baris; i++) {
                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat2.setString(1, (String) tabelPenjualan.getValueAt(i, 0));
                stat2.setString(2, (String) tabelPenjualan.getValueAt(i, 1));
                stat2.setInt(3, Integer.parseInt((String) tabelPenjualan.getValueAt(i, 6)));
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Data akan disimpan");
            int ok = JOptionPane.showConfirmDialog(null,"Tampilkan Nota Penjualan?","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (ok==0){
                File file = new File("src/Laporan/notaPenjualan.jrxml");
                HashMap hash = new HashMap();
                hash.put("kd_transaksi", tKodeTr.getText());
                JasperDesign = JRXmlLoader.load(file);
                JasperReport = JasperCompileManager.compileReport(JasperDesign);
                JasperPrint = JasperFillManager.fillReport(JasperReport, hash, conn);
                JasperViewer.viewReport(JasperPrint, false);       
            }    
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan1"+e);
        }
        
        clear();
        utama();
        tabelKosong();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        tidkategori2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tNamaKaryawan = new javax.swing.JTextField();
        tKodeKaryawan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPenjualan = new javax.swing.JTable();
        bSave = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tKodeTr = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bProduk = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tNamaProduk = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tJumlah = new javax.swing.JTextField();
        tKodeProduk = new javax.swing.JTextField();
        bTambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tTotalBayar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        tKodeKonsumen = new javax.swing.JTextField();
        bKonsumen = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tNamaKonsumen = new javax.swing.JTextField();
        tTanggal = new com.toedter.calendar.JDateChooser();
        tBayar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tKembalian = new javax.swing.JTextField();

        jLabel6.setText("jLabel6");

        tidkategori2.setEnabled(false);

        setResizable(true);
        setTitle("Transaksi Pembelian");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setName(""); // NOI18N

        jLabel1.setText("Kode Transaksi");

        tNamaKaryawan.setEnabled(false);

        tKodeKaryawan.setEnabled(false);

        jLabel2.setText("Nama Karyawan");

        jLabel4.setText("Kode Karyawan");

        tabelPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenjualanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPenjualan);

        bSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/Save.png"))); // NOI18N
        bSave.setText("  SAVE");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/Delete.png"))); // NOI18N
        bDelete.setText(" DELETE");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/Sync.png"))); // NOI18N
        bClear.setText(" CLEAR");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Transaksi Penjualan");

        tKodeTr.setEnabled(false);
        tKodeTr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKodeTrActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Produk"));

        jLabel13.setText("ID Produk");

        bProduk.setText("Cari");
        bProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProdukActionPerformed(evt);
            }
        });

        jLabel14.setText("Nama");

        tNamaProduk.setEnabled(false);
        tNamaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNamaProdukActionPerformed(evt);
            }
        });

        jLabel7.setText("Harga");

        tHarga.setEnabled(false);

        jLabel9.setText("Jumlah");

        tJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tJumlahActionPerformed(evt);
            }
        });

        tKodeProduk.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tKodeProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(tHarga)
                            .addComponent(tJumlah))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/Add.png"))); // NOI18N
        bTambah.setText(" ADD");
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        jLabel5.setText("Total Bayar :");

        tTotalBayar.setBackground(new java.awt.Color(204, 0, 0));
        tTotalBayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tTotalBayar.setForeground(new java.awt.Color(255, 255, 255));
        tTotalBayar.setCaretColor(new java.awt.Color(255, 255, 255));
        tTotalBayar.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        tTotalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTotalBayarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tanggal");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Konsumen\n"));

        jLabel17.setText("ID Konsumen");

        tKodeKonsumen.setEnabled(false);

        bKonsumen.setText("Cari");
        bKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKonsumenActionPerformed(evt);
            }
        });

        jLabel18.setText("Nama");

        tNamaKonsumen.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tKodeKonsumen, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNamaKonsumen)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bKonsumen)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tKodeKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNamaKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tTanggal.setEnabled(false);

        tBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBayarActionPerformed(evt);
            }
        });
        tBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tBayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tBayarKeyTyped(evt);
            }
        });

        jLabel10.setText("Bayar");

        jLabel11.setText("Kembalian");

        tKembalian.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tKodeTr, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tKodeKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(142, 142, 142)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(89, 89, 89)
                                                .addComponent(jLabel10))
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tKembalian)
                                            .addComponent(tBayar))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tKodeTr, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tKodeKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bSave)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // TODO add your handling code here:
        if(Integer.valueOf(tBayar.getText()) >= Integer.valueOf(tTotalBayar.getText())) {
            simpanTransaksi();
        } else {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        }
    }//GEN-LAST:event_bSaveActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabelPenjualan.getModel();
        int row = tabelPenjualan.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        kosong();
        tabelPembelian(); 
        totalBiaya();
    }//GEN-LAST:event_bClearActionPerformed

    private void tabelPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenjualanMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabelPenjualanMouseClicked

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_bTambahActionPerformed

    private void tKodeTrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeTrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeTrActionPerformed

    private void bProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProdukActionPerformed
        // TODO add your handling code here:
        produkPanelPenjualan ppp = new produkPanelPenjualan(this);
        ppp.setVisible(true);
    }//GEN-LAST:event_bProdukActionPerformed

    private void bKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKonsumenActionPerformed
        // TODO add your handling code here:
        konsumenPanel kp = new konsumenPanel(this);
        kp.setVisible(true);
    }//GEN-LAST:event_bKonsumenActionPerformed

    private void tJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tJumlahActionPerformed

    private void tNamaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNamaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaProdukActionPerformed

    private void tTotalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTotalBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTotalBayarActionPerformed

    private void tBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tBayarActionPerformed

    private void tBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tBayarKeyPressed

    }//GEN-LAST:event_tBayarKeyPressed

    private void tBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tBayarKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tBayarKeyTyped

    private void tBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tBayarKeyReleased
        // TODO add your handling code here:
        int totalBayar = Integer.valueOf(tTotalBayar.getText());
        int bayar = Integer.parseInt(tBayar.getText());
        
        int kembalian = bayar-totalBayar;
        System.out.println(bayar+"-"+totalBayar+"="+kembalian);
        tKembalian.setText(String.valueOf(kembalian));

    }//GEN-LAST:event_tBayarKeyReleased
    
    public void utama(){
        tKodeTr.setText("");
        tKodeProduk.setText("");
        tNamaProduk.setText("");
        tHarga.setText("");
        tJumlah.setText("");
        tTanggal.setDate(new Date());
        tBayar.setText("");
        tKembalian.setText("");
        autonumber();
    }
    
    public void clear(){
        tKodeKonsumen.setText("");
        tNamaKonsumen.setText("");
        tTotalBayar.setText("0");
    }
    
    public void loadData(){
        int jumlah = Integer.valueOf(tJumlah.getText());
        int harga = Integer.valueOf(tHarga.getText());
        DefaultTableModel model = (DefaultTableModel) tabelPenjualan.getModel();
        model.addRow(new Object[]{
            tKodeTr.getText(),
            tKodeProduk.getText(),
            tNamaProduk.getText(),
            tHarga.getText(),
            tKodeKonsumen.getText(),
            tNamaKonsumen.getText(),
            tJumlah.getText(),
            harga*jumlah
        });
    }
    public void totalBiaya(){
        int jumlahBaris = tabelPenjualan.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(tabelPenjualan.getValueAt(i, 6).toString());
            hargaBarang = Integer.parseInt(tabelPenjualan.getValueAt(i, 3).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        tTotalBayar.setText(String.valueOf(totalBiaya));
    }
    
    public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(tJumlah.getText());
        harga = Integer.valueOf(tHarga.getText());
        total = jumlah * harga;
        
        
        if(cekStok()) {
            tTotalBayar.setText(String.valueOf(total));
            loadData();
            totalBiaya();
            clear2();
            tKodeProduk.requestFocus();
            
        } else {
          JOptionPane.showMessageDialog(null, "Jumlah yang dimasukkan melebihi stok", "kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean cekStok() {
       int stok = 0;
       int jumlah = Integer.valueOf(tJumlah.getText());
       String sql = "Select * from produk where kd_produk = ?";
       try {
            PreparedStatement stm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stm.setString(1, tKodeProduk.getText());
            ResultSet rs=stm.executeQuery();
            while (rs.next()) {
                stok = rs.getInt("stok");
            }
            rs.last();
            if (jumlah < stok ){
                return true;
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "gagal login: "+e);     
	}
        return false;
    }
    
    public void clear2(){
        tKodeProduk.setText("");
        tNamaProduk.setText("");
        tHarga.setText("");
        tJumlah.setText("");
    }
    
    public void tabelKosong(){
        DefaultTableModel model = (DefaultTableModel) tabelPenjualan.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void tabelPembelian(){
        Object header[]={"Kode","ID Produk","Nama Produk","Harga","ID Konsumen","Nama Konsumen","jumlah", "Sub Total"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabelPenjualan.setModel(data);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bKonsumen;
    private javax.swing.JButton bProduk;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tBayar;
    public javax.swing.JTextField tHarga;
    public javax.swing.JTextField tJumlah;
    private javax.swing.JTextField tKembalian;
    private javax.swing.JTextField tKodeKaryawan;
    public javax.swing.JTextField tKodeKonsumen;
    public javax.swing.JTextField tKodeProduk;
    private javax.swing.JTextField tKodeTr;
    private javax.swing.JTextField tNamaKaryawan;
    public javax.swing.JTextField tNamaKonsumen;
    public javax.swing.JTextField tNamaProduk;
    private com.toedter.calendar.JDateChooser tTanggal;
    private javax.swing.JTextField tTotalBayar;
    private javax.swing.JTable tabelPenjualan;
    public javax.swing.JTextField tidkategori2;
    // End of variables declaration//GEN-END:variables
}
