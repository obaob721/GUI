/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPackage;

import java.awt.Color;

/**
 *
 * @author PATRICIA
 */
public class adminBlotterCRUD extends javax.swing.JFrame {
      private String fullname;
    /**
     * Creates new form adminBlotterCRUD
     */
    public adminBlotterCRUD(String fullname) {
         this.fullname = fullname;
        initComponents();
        setSize(970, 540);
        setResizable(false);
        
    }
    Color navcolor = new Color(204,255,204);
    Color headcolor = new Color(0,51,51);
    Color bodycolor = new Color(0,153,153);

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        addbutton2 = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        deletebutton = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        refresh = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        editbutton1 = new javax.swing.JPanel();
        edit1 = new javax.swing.JLabel();
        fn1 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        enterfn = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        email2 = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        ln = new javax.swing.JLabel();
        email1 = new javax.swing.JLabel();
        enterln = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        email3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        ln1 = new javax.swing.JLabel();
        enterln1 = new javax.swing.JTextField();
        ln2 = new javax.swing.JLabel();
        enterln2 = new javax.swing.JTextField();
        back = new javax.swing.JPanel();
        edit2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(0, 51, 51));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 153, 153));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BLOTTER DETAILS");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 980, 30));

        main.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 40));

        addbutton2.setBackground(new java.awt.Color(204, 255, 204));
        addbutton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        addbutton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addbutton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addbutton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addbutton2MouseExited(evt);
            }
        });

        edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setText("ADD");

        javax.swing.GroupLayout addbutton2Layout = new javax.swing.GroupLayout(addbutton2);
        addbutton2.setLayout(addbutton2Layout);
        addbutton2Layout.setHorizontalGroup(
            addbutton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addbutton2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addbutton2Layout.setVerticalGroup(
            addbutton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addbutton2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(addbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 80, 30));

        deletebutton.setBackground(new java.awt.Color(204, 255, 204));
        deletebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        deletebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletebuttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deletebuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deletebuttonMouseExited(evt);
            }
        });

        add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add.setText("DELETE");

        javax.swing.GroupLayout deletebuttonLayout = new javax.swing.GroupLayout(deletebutton);
        deletebutton.setLayout(deletebuttonLayout);
        deletebuttonLayout.setHorizontalGroup(
            deletebuttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deletebuttonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        deletebuttonLayout.setVerticalGroup(
            deletebuttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deletebuttonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 80, 30));

        refresh.setBackground(new java.awt.Color(204, 255, 204));
        refresh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshMouseExited(evt);
            }
        });

        refresh1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refresh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh1.setText("REFRESH");

        javax.swing.GroupLayout refreshLayout = new javax.swing.GroupLayout(refresh);
        refresh.setLayout(refreshLayout);
        refreshLayout.setHorizontalGroup(
            refreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, refreshLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        refreshLayout.setVerticalGroup(
            refreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, refreshLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, -1));

        editbutton1.setBackground(new java.awt.Color(204, 255, 204));
        editbutton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        editbutton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editbutton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editbutton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editbutton1MouseExited(evt);
            }
        });

        edit1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit1.setText("EDIT");

        javax.swing.GroupLayout editbutton1Layout = new javax.swing.GroupLayout(editbutton1);
        editbutton1.setLayout(editbutton1Layout);
        editbutton1Layout.setHorizontalGroup(
            editbutton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editbutton1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        editbutton1Layout.setVerticalGroup(
            editbutton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editbutton1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(editbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 80, 30));

        fn1.setBackground(new java.awt.Color(255, 255, 255));
        fn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fn1.setForeground(new java.awt.Color(255, 255, 255));
        fn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fn1.setText("Complainant:");
        main.add(fn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 130, 40));

        email.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email.setForeground(new java.awt.Color(255, 255, 255));
        email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email.setText("Incident Type:");
        main.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 130, 30));

        enterfn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterfn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterfn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterfnActionPerformed(evt);
            }
        });
        main.add(enterfn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 260, 40));

        age.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });
        main.add(age, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 260, 70));

        email2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email2.setForeground(new java.awt.Color(255, 255, 255));
        email2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email2.setText("Date Reported:");
        main.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 120, 30));

        number.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberActionPerformed(evt);
            }
        });
        main.add(number, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 260, 40));

        ln.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln.setForeground(new java.awt.Color(255, 255, 255));
        ln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln.setText("Suspect Name:");
        main.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 120, 40));

        email1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email1.setForeground(new java.awt.Color(255, 255, 255));
        email1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email1.setText("Status:");
        main.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 120, 40));

        enterln.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterlnActionPerformed(evt);
            }
        });
        main.add(enterln, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 260, 40));

        address.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        main.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 260, 70));

        email3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email3.setForeground(new java.awt.Color(255, 255, 255));
        email3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email3.setText("Location:");
        main.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 110, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Ongoing", "Settled" }));
        main.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 260, 40));

        ln1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln1.setForeground(new java.awt.Color(255, 255, 255));
        ln1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln1.setText("Witness 1:");
        main.add(ln1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 120, 30));

        enterln1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterln1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterln1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterln1ActionPerformed(evt);
            }
        });
        main.add(enterln1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 260, 40));

        ln2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln2.setForeground(new java.awt.Color(255, 255, 255));
        ln2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln2.setText("Witness 2:");
        main.add(ln2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 120, 40));

        enterln2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterln2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterln2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterln2ActionPerformed(evt);
            }
        });
        main.add(enterln2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 260, 40));

        back.setBackground(new java.awt.Color(204, 255, 204));
        back.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
        });

        edit2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit2.setText("BACK");

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addbutton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseClicked
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_addbutton2MouseClicked

    private void addbutton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseEntered
        addbutton2.setBackground(bodycolor);
    }//GEN-LAST:event_addbutton2MouseEntered

    private void addbutton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseExited
        addbutton2.setBackground(navcolor);
    }//GEN-LAST:event_addbutton2MouseExited

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked

    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseEntered
        deletebutton.setBackground(bodycolor);
    }//GEN-LAST:event_deletebuttonMouseEntered

    private void deletebuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseExited
        deletebutton.setBackground(navcolor);
    }//GEN-LAST:event_deletebuttonMouseExited

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked

    }//GEN-LAST:event_refreshMouseClicked

    private void refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseEntered
        refresh.setBackground(bodycolor);
    }//GEN-LAST:event_refreshMouseEntered

    private void refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseExited
        refresh.setBackground(navcolor);
    }//GEN-LAST:event_refreshMouseExited

    private void editbutton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseClicked

    }//GEN-LAST:event_editbutton1MouseClicked

    private void editbutton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseEntered
        editbutton1.setBackground(bodycolor);
    }//GEN-LAST:event_editbutton1MouseEntered

    private void editbutton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseExited
        editbutton1.setBackground(navcolor);
    }//GEN-LAST:event_editbutton1MouseExited

    private void enterfnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterfnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterfnActionPerformed

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberActionPerformed

    private void enterlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterlnActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void enterln1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterln1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterln1ActionPerformed

    private void enterln2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterln2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterln2ActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
            new adminBlotter(fullname).setVisible(true);
            this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
         back.setBackground(bodycolor);
    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        back.setBackground(navcolor);
    }//GEN-LAST:event_backMouseExited

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
            java.util.logging.Logger.getLogger(adminBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminBlotterCRUD("Admin User").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JPanel addbutton2;
    private javax.swing.JTextField address;
    private javax.swing.JTextField age;
    private javax.swing.JPanel back;
    private javax.swing.JPanel deletebutton;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JLabel edit2;
    private javax.swing.JPanel editbutton1;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email3;
    private javax.swing.JTextField enterfn;
    private javax.swing.JTextField enterln;
    private javax.swing.JTextField enterln1;
    private javax.swing.JTextField enterln2;
    private javax.swing.JLabel fn1;
    private javax.swing.JPanel header;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel ln;
    private javax.swing.JLabel ln1;
    private javax.swing.JLabel ln2;
    private javax.swing.JPanel main;
    private javax.swing.JTextField number;
    private javax.swing.JPanel refresh;
    private javax.swing.JLabel refresh1;
    // End of variables declaration//GEN-END:variables
}
