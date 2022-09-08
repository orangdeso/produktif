
package laporan;

//import static Home.txt_username2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class laporanpenjualanadminbulan extends javax.swing.JFrame {

    /**
     * Creates new form laporanpenjualanadminbulan
     */
    public laporanpenjualanadminbulan(String id_user) {
        initComponents();
        load_table();
        total();
        setNama(id_user);
        tampil_tanggal();
        tampil_waktu();
    } 
    
    public void setNama(String user) {
        txt_username.setText(user);
    }
      
    private void tampil_tanggal(){
        java.util.Date tglsekaran = new java.util.Date();
        SimpleDateFormat oi = new SimpleDateFormat("EEEE dd MMMM YYYY", Locale.getDefault());
        String tanggal = oi.format(tglsekaran);
        txt_tanggal.setText(tanggal);
    }
    
    private void tampil_waktu(){
        ActionListener taskPerformer = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";
            
                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();
            
                if(nilai_jam <= 9) nol_jam = "0";
                if(nilai_menit <= 9) nol_jam = "0";
                if(nilai_detik <= 9) nol_jam = "0";
            
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
            
                txt_waktu.setText(jam + ":" + menit + ":" + detik + "");
            }      
        };
        new Timer(1000, taskPerformer).start(); 
    }
    
     private void load_table() {
        DefaultTableModel tbl = new DefaultTableModel();
    
        tbl.addColumn("No.");
        tbl.addColumn("Total Per Bulan");
        tbl.addColumn("Bulan Ke");
        tbl.addColumn("Tahun");
    
        String tahun = String.valueOf(pilihtahun.getYear());
        try{
            int  no = 1;
            String sql = "select sum(tot_bayar) as TotalPerbulan, "
                 + "MONTH(tanggal_transaksi) as Bulan_Ke, YEAR(tanggal_transaksi) "
                 + "as Tahun from transaksi where MONTH(tanggal_transaksi) >= 1 "
                 + "AND YEAR(tanggal_transaksi)= '"+tahun+"'"
                 + "Group By MONTH(tanggal_transaksi);";
            java.sql.Connection conn = (Connection) db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement(); 
            java.sql.ResultSet rs = stm.executeQuery(sql);
         while (rs.next()) {
             tbl.addRow(new Object [ ] {
                 no,
                 rs.getString("TotalPerbulan"),
                 rs.getString("Bulan_Ke"),
                 rs.getString("Tahun")
            });
            jTable1.setModel(tbl);
            no++;
            }
        } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Koneksi Database Gagal"+ e.getMessage());
    }
    total();
     }
     
      private void total() {
        String tahun = String.valueOf(pilihtahun.getYear());
        try {
            String sql ="select sum(tot_bayar) from transaksi "
                    + "where YEAR(tanggal_transaksi)='"+tahun+"'";
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            
            if(rs.next()){
                txt_pendapat.setText(rs.getString(1));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTN_CARI = new javax.swing.JButton();
        BTN_CETAK = new javax.swing.JButton();
        txt_pendapat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pilihtahun = new com.toedter.calendar.JYearChooser();
        txt_tanggal = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(245, 150, 92));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/back.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 720, 60, 40));

        jTable1.setBackground(new java.awt.Color(255, 204, 153));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setRowHeight(32);
        jTable1.setSelectionBackground(new java.awt.Color(245, 150, 92));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 760, 400));

        BTN_CARI.setBackground(new java.awt.Color(245, 150, 92));
        BTN_CARI.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        BTN_CARI.setForeground(new java.awt.Color(255, 255, 255));
        BTN_CARI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/magnifier.png"))); // NOI18N
        BTN_CARI.setText("CARI");
        BTN_CARI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_CARI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BTN_CARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CARIActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_CARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 170, 100, 40));

        BTN_CETAK.setBackground(new java.awt.Color(245, 150, 92));
        BTN_CETAK.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        BTN_CETAK.setForeground(new java.awt.Color(255, 255, 255));
        BTN_CETAK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/printing.png"))); // NOI18N
        BTN_CETAK.setText("CETAK");
        BTN_CETAK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_CETAK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BTN_CETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CETAKActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_CETAK, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 680, 110, 40));

        txt_pendapat.setEditable(false);
        txt_pendapat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txt_pendapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pendapatActionPerformed(evt);
            }
        });
        getContentPane().add(txt_pendapat, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 680, 210, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Total Pendapatan");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 650, -1, -1));

        pilihtahun.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(pilihtahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 170, 80, 40));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Username");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambarlaporanadmin/Laporan penjualan2 Admin REVISI.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new laporanadminpenjualan(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BTN_CARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CARIActionPerformed
        // TODO add your handling code here:
        load_table();
    }//GEN-LAST:event_BTN_CARIActionPerformed

    private void txt_pendapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pendapatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pendapatActionPerformed

    private void BTN_CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CETAKActionPerformed
        // TODO add your handling code here:
        cetak();
    }//GEN-LAST:event_BTN_CETAKActionPerformed
private void cetak() {
            try {
                jTable1.print();
                
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null,  "Maaf Data Table Anda Kosong");
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
            java.util.logging.Logger.getLogger(laporanpenjualanadminbulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporanpenjualanadminbulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporanpenjualanadminbulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporanpenjualanadminbulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporanpenjualanadminbulan(txt_username.getText()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_CARI;
    private javax.swing.JButton BTN_CETAK;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JYearChooser pilihtahun;
    private javax.swing.JTextField txt_pendapat;
    private javax.swing.JLabel txt_tanggal;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
