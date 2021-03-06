
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wildan
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    String user;
    
    public Main(String u) {
        initComponents();
        user = u;
        try {
            selectData();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 29, 82));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Rental Motor");
        jPanel1.add(jLabel3);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 680, 50);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nama", "Alamat", "No Struk", "No Pol", "Tgl Pinjam", "Tgl Kembali", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblData.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblData);
        if (tblData.getColumnModel().getColumnCount() > 0) {
            tblData.getColumnModel().getColumn(0).setResizable(false);
            tblData.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 150, 590, 180);

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(50, 70, 130, 30);

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(50, 110, 130, 30);

        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(530, 110, 100, 30);

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(410, 110, 110, 30);

        jDateChooser1.setToolTipText("");
        getContentPane().add(jDateChooser1);
        jDateChooser1.setBounds(410, 70, 140, 30);

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(550, 70, 80, 30);

        setSize(new java.awt.Dimension(692, 389));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void search() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(jDateChooser1.getDate());
        String kolom[] = {"Id","Nama","Alamat","No Struk","No Pol","Tgl Pinjam","Tgl Kembali","Harga"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        //SELECT * FROM `rental` WHERE tgl_pinjam <= 101217 AND tgl_kembali >= 101217
        String SQL = "SELECT * FROM rental WHERE tgl_pinjam <= '"+date+"' AND tgl_kembali >= '"+date+"' ";
        System.out.println(SQL);
        ResultSet rs = KoneksiDB.executeQuery(SQL);
        try{
            while(rs.next()) {
                String Id = rs.getString(1);
                String Nama = rs.getString(2);
                String Alamat = rs.getString(3);
                String NoStruk = rs.getString(4);
                String NoPol = rs.getString(5);
                String TglPinjam = rs.getString(6);
                String TglKembali = rs.getString(7);
                Date pinjam = new SimpleDateFormat("yyyy-MM-dd").parse(TglPinjam);
                Date kembali = new SimpleDateFormat("yyy-MM-dd").parse(TglKembali);
                long intHarga = ((kembali.getTime() - pinjam.getTime())/ (1000 * 60 * 60 * 24))*20000;
                String Harga = String.valueOf(intHarga);
                String data[] = {Id,Nama,Alamat,NoStruk,NoPol,TglPinjam,TglKembali,Harga};
                dtm.addRow(data);
            }
        } catch (SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        }
        tblData.setModel(dtm);
    }
    public void selectData() throws ParseException {
        String kolom[] = {"Id","Nama","Alamat","No Struk","No Pol","Tgl Pinjam","Tgl Kembali","Harga"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM `rental`";
        ResultSet rs = KoneksiDB.executeQuery(SQL);
        try{
            while(rs.next()) {
                String Id = rs.getString(1);
                String Nama = rs.getString(2);
                String Alamat = rs.getString(3);
                String NoStruk = rs.getString(4);
                String NoPol = rs.getString(5);
                String TglPinjam = rs.getString(6);
                String TglKembali = rs.getString(7);
                Date pinjam = new SimpleDateFormat("yyyy-MM-dd").parse(TglPinjam);
                Date kembali = new SimpleDateFormat("yyy-MM-dd").parse(TglKembali);
                long intHarga = ((kembali.getTime() - pinjam.getTime())/ (1000 * 60 * 60 * 24))*20000;
                String Harga = String.valueOf(intHarga);
                String data[] = {Id,Nama,Alamat,NoStruk,NoPol,TglPinjam,TglKembali,Harga};
                dtm.addRow(data);
            }
        } catch (SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        }
        tblData.setModel(dtm);
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MessageFormat header = new MessageFormat("Rental Motor");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try{
            tblData.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
        } catch(java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            selectData();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int baris = tblData.getSelectedRow();
        if(baris != -1) {
            String id = tblData.getValueAt(baris, 0).toString();
            String SQL = "DELETE FROM rental WHERE id = '"+id+"'";
            
            int status = KoneksiDB.execute(SQL);
            if(status == 1) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus","Sukses", JOptionPane.INFORMATION_MESSAGE);
                try {
                    selectData();
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus","Gagal", JOptionPane.WARNING_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus","Gagal", JOptionPane.WARNING_MESSAGE);
        }
        try {
            selectData();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new AddData(user).show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            search();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
