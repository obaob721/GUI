/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPackage;

import config.dbConnector;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author PATRICIA
 */
public class adminLogs extends javax.swing.JFrame {

    private String fullname;
    private static String userImagePath = null;

    /**
     * Creates new form adminLogs
     */
    public adminLogs(String fullname, String imgPath) {
        initComponents();
        displayData();
        this.fullname = fullname;
        setLocationRelativeTo(null);

        userImagePath = imgPath;

    }
    Color navcolor = new Color(0,51,51);
    Color headcolor = new Color(0,153,153);
    Color bodycolor = new Color(0,153,153);
    

    public void displayData() {
        try {
            dbConnector dbc = new dbConnector();
            String query = "SELECT l.logs_id, CONCAT(u.user_id, ' - ', u.use_type) AS user_info, "
                    + "l.logs_action, l.logs_date "
                    + "FROM system_logs l "
                    + "JOIN user_table u ON l.user_id = u.user_id "
                    + "ORDER BY l.logs_date DESC";
            ResultSet rs = dbc.getData(query);
            logs.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
    
    private void highlightRow() {
    String searchText = searchlogs.getText().trim().toLowerCase();

    if (searchText.isEmpty()) {
        return;
    }

    logs.clearSelection(); // Clear previous selection
    boolean matchFound = false;

    for (int i = 0; i < logs.getRowCount(); i++) { // Corrected loop condition
        for (int j = 0; j < logs.getColumnCount(); j++) { // Use 0-based index
            Object cellValue = logs.getValueAt(i, j);

            if (cellValue != null) {
                String cellText = cellValue.toString().trim().toLowerCase();

                if (cellText.contains(searchText)) {
                    logs.addRowSelectionInterval(i, i); // Select row
                    matchFound = true;
                    break; // Exit column loop once a match is found
                }
            }
        }
    }

    if (matchFound) {
        // Scroll to the first selected row
        int firstSelectedRow = logs.getSelectedRow();
        if (firstSelectedRow != -1) {
            logs.scrollRectToVisible(logs.getCellRect(firstSelectedRow, 0, true));
        }
    } else {
        JOptionPane.showMessageDialog(null, "No matching record found!", "Search", JOptionPane.INFORMATION_MESSAGE);
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
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logs1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        settings1 = new javax.swing.JLabel();
        searchbtn = new javax.swing.JPanel();
        search = new javax.swing.JLabel();
        searchlogs = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 153, 153));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Logs");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 590, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-refresh-32.png"))); // NOI18N
        header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, -1, -1));

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        logs.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(logs);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 960, 360));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Recent Activities:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 130, 40));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header1.setBackground(new java.awt.Color(0, 153, 153));
        header1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        header1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("My Logs");
        header1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 590, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-refresh-32.png"))); // NOI18N
        header1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, -1, -1));

        jPanel2.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        logs1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(logs1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 960, 360));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Recent Activities:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 130, 40));

        settings1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        settings1.setForeground(new java.awt.Color(255, 255, 255));
        settings1.setText("Account Settings");
        settings1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settings1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settings1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settings1MouseExited(evt);
            }
        });
        jPanel2.add(settings1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 120, 40));

        searchbtn.setBackground(new java.awt.Color(0, 51, 51));
        searchbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        searchbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchbtnMouseExited(evt);
            }
        });

        search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        search.setText("SEARCH");

        searchlogs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        searchlogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchlogsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchbtnLayout = new javax.swing.GroupLayout(searchbtn);
        searchbtn.setLayout(searchbtnLayout);
        searchbtnLayout.setHorizontalGroup(
            searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchbtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(searchlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchbtnLayout.setVerticalGroup(
            searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchbtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void settings1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settings1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_settings1MouseClicked

    private void settings1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settings1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_settings1MouseEntered

    private void settings1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settings1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_settings1MouseExited

    private void searchlogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchlogsActionPerformed

    }//GEN-LAST:event_searchlogsActionPerformed

    private void searchbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbtnMouseClicked
        highlightRow();
    }//GEN-LAST:event_searchbtnMouseClicked

    private void searchbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbtnMouseEntered
        searchbtn.setBackground(bodycolor);
    }//GEN-LAST:event_searchbtnMouseEntered

    private void searchbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbtnMouseExited
        searchbtn.setBackground(navcolor);
    }//GEN-LAST:event_searchbtnMouseExited

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
            java.util.logging.Logger.getLogger(adminLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 String imgPath = "path/to/default/image.png";
                new adminLogs("Admin User", imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable logs;
    private javax.swing.JTable logs1;
    private javax.swing.JLabel search;
    private javax.swing.JPanel searchbtn;
    private javax.swing.JTextField searchlogs;
    private javax.swing.JLabel settings1;
    // End of variables declaration//GEN-END:variables
}
